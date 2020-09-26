package com.jhispter.nigeria.service.mapper;


import com.jhispter.nigeria.domain.*;
import com.jhispter.nigeria.service.dto.SmsNotificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SmsNotification} and its DTO {@link SmsNotificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SmsNotificationMapper extends EntityMapper<SmsNotificationDTO, SmsNotification> {



    default SmsNotification fromId(Long id) {
        if (id == null) {
            return null;
        }
        SmsNotification smsNotification = new SmsNotification();
        smsNotification.setId(id);
        return smsNotification;
    }
}
