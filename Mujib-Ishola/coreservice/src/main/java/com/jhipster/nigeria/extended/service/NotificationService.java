package com.jhipster.nigeria.extended.service;

import com.jhipster.nigeria.extended.dto.OpenAccountResponseDto;
import com.jhipster.nigeria.service.dto.ProfileDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final Logger log = LoggerFactory.getLogger(NotificationService.class);


    public void sendRegistrationNotification(ProfileDTO profile) {
    }

    public void sendActivationNotification(OpenAccountResponseDto openAccount) {
    }

    public void sendPasswordResetNotification(ProfileDTO profile, String passwordResetKey) {
    }
}
