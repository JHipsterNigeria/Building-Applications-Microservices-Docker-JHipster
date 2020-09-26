package com.jhispter.nigeria.repository;

import com.jhispter.nigeria.domain.EmailNotification;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EmailNotification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmailNotificationRepository extends JpaRepository<EmailNotification, Long> {
}
