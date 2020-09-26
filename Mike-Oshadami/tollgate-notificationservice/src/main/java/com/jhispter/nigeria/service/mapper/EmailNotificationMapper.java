package com.jhispter.nigeria.service.mapper;


import com.jhispter.nigeria.domain.*;
import com.jhispter.nigeria.service.dto.EmailNotificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EmailNotification} and its DTO {@link EmailNotificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmailNotificationMapper extends EntityMapper<EmailNotificationDTO, EmailNotification> {



    default EmailNotification fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.setId(id);
        return emailNotification;
    }
}
