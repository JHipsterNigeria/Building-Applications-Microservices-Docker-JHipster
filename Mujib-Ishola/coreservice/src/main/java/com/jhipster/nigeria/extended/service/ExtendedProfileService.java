package com.jhipster.nigeria.extended.service;


import com.jhipster.nigeria.domain.enumeration.ProfileType;
import com.jhipster.nigeria.extended.client.UaaFeignClient;
import com.jhipster.nigeria.extended.config.ExtendedConstants;
import com.jhipster.nigeria.extended.dto.CustomerProfileResponseDto;
import com.jhipster.nigeria.extended.dto.DefaultApiResponse;
import com.jhipster.nigeria.extended.exception.AccountAlreadyRegisterException;
import com.jhipster.nigeria.extended.service.dto.UserDTO;
import com.jhipster.nigeria.extended.web.rest.vm.ManagedUserVM;
import com.jhipster.nigeria.service.ProfileQueryService;
import com.jhipster.nigeria.service.ProfileService;
import com.jhipster.nigeria.service.dto.ProfileCriteria;
import com.jhipster.nigeria.service.dto.ProfileDTO;
import feign.FeignException;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExtendedProfileService {

    private UaaFeignClient uaaFeignClient;

    private ProfileService profileService;

    private UserService userService;

    @Value("${default.app.lang:en}")
    private String defaultLang;

    private static final String CUSTOMER_PROFILE = "CUSTOMER";
    private static final String AGENT_PROFILE = "AGENT";
    private static final String ADMIN_PROFILE = "ADMIN";

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private ProfileQueryService profileQueryService;

    public ExtendedProfileService(UaaFeignClient uaaFeignClient, ProfileService profileService, ProfileQueryService profileQueryService, UserService userService) {
        this.uaaFeignClient = uaaFeignClient;
        this.profileService = profileService;
        this.profileQueryService = profileQueryService;
        this.userService = userService;
    }

    public ProfileDTO doCustomerRegistration(ManagedUserVM accountDto) throws AccountAlreadyRegisterException, Exception {
        ProfileDTO profileDTO = new ProfileDTO();
        String login = accountDto.getLogin();
        UserDTO user = new UserDTO();
        UserDTO userInDb = null;
        userInDb = getUserByLogin(login);
        if (userInDb == null) {
            ManagedUserVM newUser = new ManagedUserVM();
            newUser.setEmail(accountDto.getEmail());
            newUser.setFirstName(accountDto.getFirstName());
            newUser.setLastName(accountDto.getLastName());
            newUser.setLogin(login);
            newUser.setPassword(accountDto.getPassword());
            newUser.setLangKey(defaultLang);
            newUser.setActivationKey(RandomStringUtils.random(20, 0, 0, true, true, null, SECURE_RANDOM));
            uaaFeignClient.registerProfile(newUser);
            user = getUserByLogin(login);
            profileDTO.setLogin(login);
            profileDTO.setEmailNotifications(Boolean.FALSE);
            profileDTO.setSmsNotifications(Boolean.FALSE);
            profileDTO.setUserId(user.getId());
            profileDTO.setFirstName(accountDto.getFirstName());
            profileDTO.setEmail(accountDto.getEmail());
            profileDTO.setPhone(accountDto.getPhoneNumber());
            profileDTO.setLastName(accountDto.getLastName());
            profileDTO.setOptActivationKey(RandomStringUtils.random(6, 0, 0, false, true, null, SECURE_RANDOM));
            profileDTO.setActivationKey(newUser.getActivationKey());
        } else {
            throw new AccountAlreadyRegisterException();
        }
        profileDTO.setUserId(user.getId());
        profileDTO = profileService.save(profileDTO);
        return profileDTO;
    }


    public UserDTO getUserByLogin(String login) {
        return userService.getUserByLogin(login);
    }

    public ProfileDTO getProfileByLogin(String login) {
        UserDTO user = null;
        try {
            user = uaaFeignClient.getUser(login);
        } catch (FeignException.FeignClientException e) {
            if (!Integer.valueOf(HttpStatus.NOT_FOUND.value()).equals(e.status())) {
                //  throw new Exception("Error processing request");
            }
        }
        if (user != null) {
            ProfileCriteria profileCriteria = new ProfileCriteria();
            LongFilter userIdFilter = new LongFilter();
            userIdFilter.setEquals(user.getId());
            profileCriteria.setUserId(userIdFilter);
            return profileQueryService.findByCriteria(profileCriteria).stream().findFirst().orElse(null);
        }
        return null;
    }


    public UserDTO getUserByUserId(Long userId) {
        try {
            return uaaFeignClient.getUser(userId);
        } catch (FeignException.FeignClientException e) {
            if (!Integer.valueOf(HttpStatus.NOT_FOUND.value()).equals(e.status())) {
                //  throw new Exception("Error processing request");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error");
        }
        return null;
    }

    public ProfileDTO activateRegistrationByKey(String activationKey) {
        ProfileCriteria profileCriteria = new ProfileCriteria();
        StringFilter activationKeyFilter = new StringFilter();
        activationKeyFilter.setEquals(activationKey);
        profileCriteria.setActivationKey(activationKeyFilter);
        ProfileDTO profile = profileQueryService.findByCriteria(profileCriteria).stream().findFirst().orElse(null);
        if (profile != null) {
            UserDTO user = getUserByUserId(profile.getUserId());
            if (user != null && profile.getUserId() == user.getId()) {
                uaaFeignClient.activateProfile(profile.getActivationKey());
                profile.setOptActivationKey(null);
                profile.setActivationKey(null);
                profileService.save(profile);
            } else {
                profile = null;
            }
        }
        return profile;
    }

    public ProfileDTO activateRegistrationByOtp(String activationKey, String otpActivationKey) {
        ProfileCriteria profileCriteria = new ProfileCriteria();
        StringFilter activationKeyFilter = new StringFilter();
        activationKeyFilter.setEquals(activationKey);
        profileCriteria.setActivationKey(activationKeyFilter);
        ProfileDTO profile = profileQueryService.findByCriteria(profileCriteria).stream().findFirst().orElse(null);
        if (profile != null && otpActivationKey.equals(profile.getOptActivationKey())) {
            UserDTO user = getUserByUserId(profile.getUserId());
            if (user != null && profile.getUserId().compareTo(user.getId()) == 0) {
                uaaFeignClient.activateProfile(profile.getActivationKey());
                profile.setOptActivationKey(null);
                profile.setActivationKey(null);
                profileService.save(profile);
            } else {
                profile = null;
            }
        }
        return profile;
    }

    public void requestPasswordReset(String mail, String key) {
        HashMap<String, String> resetRequest = new HashMap<>();
        resetRequest.put("email", mail);
        resetRequest.put("key", key);
        uaaFeignClient.requestPasswordReset(resetRequest);
    }

}
