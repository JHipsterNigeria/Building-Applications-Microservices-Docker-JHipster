package com.jhipster.nigeria.extended.service;

import com.jhipster.nigeria.extended.client.UaaFeignClient;
import com.jhipster.nigeria.extended.dto.CustomerRegistrationRequestDto;
import com.jhipster.nigeria.extended.exception.AccountAlreadyRegisterException;
import com.jhipster.nigeria.extended.service.dto.UserDTO;
import com.jhipster.nigeria.extended.web.rest.vm.ManagedUserVM;
import com.jhipster.nigeria.service.ProfileService;
import com.jhipster.nigeria.service.dto.ProfileDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class RegistrationService {

    @Value("${default.app.lang:en}")
    private String defaultLang;

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private final UaaFeignClient uaaFeignClient;
    private final UserService userService;
    private final ProfileService profileService;

    public RegistrationService(UaaFeignClient uaaFeignClient, UserService userService, ProfileService profileService) {
        this.uaaFeignClient = uaaFeignClient;
        this.userService = userService;
        this.profileService = profileService;
    }

    public ProfileDTO doCustomerRegistration(CustomerRegistrationRequestDto requestDto) throws Exception, AccountAlreadyRegisterException {
        ProfileDTO profileDTO = new ProfileDTO();
        String email = requestDto.getEmail();
        UserDTO userInDb = userService.getUserByEmail(email);
        if (userInDb == null) {
            ManagedUserVM newUser = createUser(requestDto);
            UserDTO user = userService.getUserByLogin(email);
            if (user == null) {
                throw new Exception("Error creating profile");

            } else {
                user.setActivationKey(newUser.getActivationKey());
                profileDTO = createUserProfile(requestDto, user);
            }
        } else {
            throw new AccountAlreadyRegisterException();
        }
        return profileDTO;
    }

    private ManagedUserVM createUser(CustomerRegistrationRequestDto requestDto) {
        ManagedUserVM newUser = new ManagedUserVM();
        newUser.setFirstName(requestDto.getFirstName());
        newUser.setLastName(requestDto.getLastName());
        newUser.setEmail(requestDto.getEmail());
        newUser.setLogin(requestDto.getEmail());
        newUser.setPassword(requestDto.getEmail());
        newUser.setImageUrl(requestDto.getPhoto() != null ? saveImage(requestDto.getPhoto()) : null);
        newUser.setLangKey(defaultLang);
        newUser.setActivationKey(RandomStringUtils.random(20, 0, 0, true, true, null, SECURE_RANDOM));
        uaaFeignClient.registerProfile(newUser);
        return newUser;
    }

    private ProfileDTO createUserProfile(CustomerRegistrationRequestDto requestDto, UserDTO user) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setLogin(requestDto.getEmail());
        profileDTO.setEmailNotifications(Boolean.FALSE);
        profileDTO.setSmsNotifications(Boolean.FALSE);
        profileDTO.setUserId(user.getId());
        profileDTO.setFirstName(requestDto.getFirstName());
        profileDTO.setLastName(requestDto.getLastName());
        profileDTO.setEmail(requestDto.getEmail());
        profileDTO.setPhone(requestDto.getPhoneNumber());
        profileDTO.setLang(defaultLang);
        profileDTO.setOptActivationKey(RandomStringUtils.random(6, 0, 0, false, true, null, SECURE_RANDOM));
        profileDTO.setActivationKey(user.getActivationKey());
        profileDTO = profileService.save(profileDTO);
        return profileDTO;
    }

    private String saveImage(String image) {
        return image;
    }

}
