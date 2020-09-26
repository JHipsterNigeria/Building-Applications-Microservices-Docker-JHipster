package com.jhipster.nigeria.extended.web.rest;

import com.jhipster.nigeria.extended.config.ApplicationUrl;
import com.jhipster.nigeria.extended.config.ExtendedConstants;
import com.jhipster.nigeria.extended.dto.CustomerRegistrationRequestDto;
import com.jhipster.nigeria.extended.dto.DefaultApiResponse;
import com.jhipster.nigeria.extended.dto.OpenAccountResponseDto;
import com.jhipster.nigeria.extended.exception.AccountAlreadyRegisterException;
import com.jhipster.nigeria.extended.service.ExtendedProfileService;
import com.jhipster.nigeria.extended.service.NotificationService;
import com.jhipster.nigeria.extended.service.RegistrationService;
import com.jhipster.nigeria.service.dto.ProfileDTO;
import com.jhipster.nigeria.service.dto.VirtualAccountDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = {ApplicationUrl.BASE_CONTEXT_URL, ApplicationUrl.API_CONTEXT_URL, ApplicationUrl.API_V1_CONTEXT_URL})
public class RegistrationController extends BaseAccountController {

    private final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private Environment env;

    @Value("${application.demoMode.enable:false}")
    private boolean demoMode;

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private final NotificationService notificationService;

    private final ExtendedProfileService profileService;

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService, ExtendedProfileService profileService,
                                  NotificationService notificationService) {
        this.registrationService = registrationService;
        this.profileService = profileService;
        this.notificationService = notificationService;
    }

    @PostMapping(ApplicationUrl.REGISTER_PROFILE)
    public DefaultApiResponse registerCustomer(@Valid @RequestBody CustomerRegistrationRequestDto requestDto) {
        DefaultApiResponse response = new DefaultApiResponse();
        response.setStatus(ExtendedConstants.ResponseStatus.API_ERROR_STATUS.getCode());
        try {
                ProfileDTO profile = registrationService.doCustomerRegistration(requestDto);
                notificationService.sendRegistrationNotification(profile);
                log.info("Activation Key {} , Otp Activation Key {} ", profile.getActivationKey(), profile.getOptActivationKey());
                Map<String, String> data = new HashMap<>();
                data.put("key", profile.getActivationKey());
                if (demoMode) {
                    data.put("otp", profile.getOptActivationKey());
                }
                response.setData(data);
                response.setStatus(ExtendedConstants.ResponseStatus.API_SUCCESS_STATUS.getCode());
                response.setMessage("Profile created successfully");
        } catch (AccountAlreadyRegisterException e) {
            response.setStatus(ExtendedConstants.ResponseStatus.API_FAIL_STATUS.getCode());
            response.setMessage("Profile already registered");
        } catch (Exception e) {
            response.setMessage("Error processing request");
        }
        return response;
    }


    @GetMapping(ApplicationUrl.ACTIVATE_PROFILE)
    public DefaultApiResponse activateProfile(@RequestParam(value = "otp") String otp, @RequestParam(value = "key") String key) {
        DefaultApiResponse response = new DefaultApiResponse();
        response.setStatus(ExtendedConstants.ResponseStatus.API_ERROR_STATUS.getCode());
        ProfileDTO profile = profileService.activateRegistrationByOtp(key, otp);
        if (profile != null) {
            try {
                VirtualAccountDTO account = accountService.findAccount(profile.getPhone());
                if (account != null) {
                    throw new AccountAlreadyRegisterException();
                } else {
                    account = accountService.createAccount(profile);
                    if (account != null) {
                        OpenAccountResponseDto openAccount = new OpenAccountResponseDto();
                        openAccount.setAccountNumber(account.getAccountNumber());
                        openAccount.setCustomerId(account.getCustomerId());
                        openAccount.setAccountOpeningDate(String.valueOf(Instant.now()));
                        notificationService.sendActivationNotification(openAccount);
                        response.setStatus(ExtendedConstants.ResponseStatus.API_SUCCESS_STATUS.getCode());
                        response.setMessage("Profile activated");
                        openAccount.setProcessCode(ExtendedConstants.ResponseCode.SUCCESS.getCode());
                        openAccount.setProcessMessage(String.format("New Account %s opened", openAccount.getAccountNumber()));
                        response.setData(openAccount);
                    } else {
                        response.setStatus(ExtendedConstants.ResponseCode.INVALID_ACCOUNT.getCode());
                        response.setMessage(String.format("Invalid Account %s", account.getAccountNumber()));
                    }
                }
            }
            catch (AccountAlreadyRegisterException e) {
                response.setStatus(ExtendedConstants.ResponseCode.ACCOUNT_ALREADY_EXITS.getCode());
                response.setMessage(String.format("Account %s already exists", profile.getPhone()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.setStatus(ExtendedConstants.ResponseStatus.API_FAIL_STATUS.getCode());
            response.setMessage("Invalid key or otp specified");
        }
        return response;
    }

    @PostMapping(ApplicationUrl.COMPLETE_RESET_PASSWORD)
    public void requestPasswordReset(@RequestBody Map<String, String> requestMap) {
        String email = requestMap.get("email");
        ProfileDTO profile = profileService.getProfileByLogin(email);
        String passwordResetKey = requestMap.get("passwordResetKey");
        if (profile != null) {
            profileService.requestPasswordReset(email, passwordResetKey);
            notificationService.sendPasswordResetNotification(profile, passwordResetKey);
        } else {
            log.warn("Password reset requested for non existing mail");
        }
    }

}
