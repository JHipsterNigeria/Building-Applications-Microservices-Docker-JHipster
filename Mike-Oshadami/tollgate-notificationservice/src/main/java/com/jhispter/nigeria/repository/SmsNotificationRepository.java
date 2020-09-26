package com.jhispter.nigeria.repository;

import com.jhispter.nigeria.domain.SmsNotification;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SmsNotification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SmsNotificationRepository extends JpaRepository<SmsNotification, Long> {
}
