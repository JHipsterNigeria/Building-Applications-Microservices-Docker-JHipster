package com.jhispter.nigeria.service;

import com.jhispter.nigeria.domain.SmsNotification;
import com.jhispter.nigeria.repository.SmsNotificationRepository;
import com.jhispter.nigeria.service.dto.SmsNotificationDTO;
import com.jhispter.nigeria.service.mapper.SmsNotificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SmsNotification}.
 */
@Service
@Transactional
public class SmsNotificationService {

    private final Logger log = LoggerFactory.getLogger(SmsNotificationService.class);

    private final SmsNotificationRepository smsNotificationRepository;

    private final SmsNotificationMapper smsNotificationMapper;

    public SmsNotificationService(SmsNotificationRepository smsNotificationRepository, SmsNotificationMapper smsNotificationMapper) {
        this.smsNotificationRepository = smsNotificationRepository;
        this.smsNotificationMapper = smsNotificationMapper;
    }

    /**
     * Save a smsNotification.
     *
     * @param smsNotificationDTO the entity to save.
     * @return the persisted entity.
     */
    public SmsNotificationDTO save(SmsNotificationDTO smsNotificationDTO) {
        log.debug("Request to save SmsNotification : {}", smsNotificationDTO);
        SmsNotification smsNotification = smsNotificationMapper.toEntity(smsNotificationDTO);
        smsNotification = smsNotificationRepository.save(smsNotification);
        return smsNotificationMapper.toDto(smsNotification);
    }

    /**
     * Get all the smsNotifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SmsNotificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SmsNotifications");
        return smsNotificationRepository.findAll(pageable)
            .map(smsNotificationMapper::toDto);
    }


    /**
     * Get one smsNotification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SmsNotificationDTO> findOne(Long id) {
        log.debug("Request to get SmsNotification : {}", id);
        return smsNotificationRepository.findById(id)
            .map(smsNotificationMapper::toDto);
    }

    /**
     * Delete the smsNotification by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SmsNotification : {}", id);

        smsNotificationRepository.deleteById(id);
    }
}
