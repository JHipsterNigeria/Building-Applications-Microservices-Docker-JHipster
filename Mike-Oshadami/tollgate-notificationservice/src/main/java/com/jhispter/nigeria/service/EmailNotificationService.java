package com.jhispter.nigeria.service;

import com.jhispter.nigeria.domain.EmailNotification;
import com.jhispter.nigeria.repository.EmailNotificationRepository;
import com.jhispter.nigeria.service.dto.EmailNotificationDTO;
import com.jhispter.nigeria.service.mapper.EmailNotificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EmailNotification}.
 */
@Service
@Transactional
public class EmailNotificationService {

    private final Logger log = LoggerFactory.getLogger(EmailNotificationService.class);

    private final EmailNotificationRepository emailNotificationRepository;

    private final EmailNotificationMapper emailNotificationMapper;

    public EmailNotificationService(EmailNotificationRepository emailNotificationRepository, EmailNotificationMapper emailNotificationMapper) {
        this.emailNotificationRepository = emailNotificationRepository;
        this.emailNotificationMapper = emailNotificationMapper;
    }

    /**
     * Save a emailNotification.
     *
     * @param emailNotificationDTO the entity to save.
     * @return the persisted entity.
     */
    public EmailNotificationDTO save(EmailNotificationDTO emailNotificationDTO) {
        log.debug("Request to save EmailNotification : {}", emailNotificationDTO);
        EmailNotification emailNotification = emailNotificationMapper.toEntity(emailNotificationDTO);
        emailNotification = emailNotificationRepository.save(emailNotification);
        return emailNotificationMapper.toDto(emailNotification);
    }

    /**
     * Get all the emailNotifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EmailNotificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EmailNotifications");
        return emailNotificationRepository.findAll(pageable)
            .map(emailNotificationMapper::toDto);
    }


    /**
     * Get one emailNotification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EmailNotificationDTO> findOne(Long id) {
        log.debug("Request to get EmailNotification : {}", id);
        return emailNotificationRepository.findById(id)
            .map(emailNotificationMapper::toDto);
    }

    /**
     * Delete the emailNotification by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EmailNotification : {}", id);

        emailNotificationRepository.deleteById(id);
    }
}
