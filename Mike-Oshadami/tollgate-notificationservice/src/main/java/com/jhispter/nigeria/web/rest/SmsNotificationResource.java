package com.jhispter.nigeria.web.rest;

import com.jhispter.nigeria.service.SmsNotificationService;
import com.jhispter.nigeria.web.rest.errors.BadRequestAlertException;
import com.jhispter.nigeria.service.dto.SmsNotificationDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.jhispter.nigeria.domain.SmsNotification}.
 */
@RestController
@RequestMapping("/api")
public class SmsNotificationResource {

    private final Logger log = LoggerFactory.getLogger(SmsNotificationResource.class);

    private static final String ENTITY_NAME = "tollGateNotificationServiceSmsNotification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SmsNotificationService smsNotificationService;

    public SmsNotificationResource(SmsNotificationService smsNotificationService) {
        this.smsNotificationService = smsNotificationService;
    }

    /**
     * {@code POST  /sms-notifications} : Create a new smsNotification.
     *
     * @param smsNotificationDTO the smsNotificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new smsNotificationDTO, or with status {@code 400 (Bad Request)} if the smsNotification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sms-notifications")
    public ResponseEntity<SmsNotificationDTO> createSmsNotification(@RequestBody SmsNotificationDTO smsNotificationDTO) throws URISyntaxException {
        log.debug("REST request to save SmsNotification : {}", smsNotificationDTO);
        if (smsNotificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new smsNotification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SmsNotificationDTO result = smsNotificationService.save(smsNotificationDTO);
        return ResponseEntity.created(new URI("/api/sms-notifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sms-notifications} : Updates an existing smsNotification.
     *
     * @param smsNotificationDTO the smsNotificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated smsNotificationDTO,
     * or with status {@code 400 (Bad Request)} if the smsNotificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the smsNotificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sms-notifications")
    public ResponseEntity<SmsNotificationDTO> updateSmsNotification(@RequestBody SmsNotificationDTO smsNotificationDTO) throws URISyntaxException {
        log.debug("REST request to update SmsNotification : {}", smsNotificationDTO);
        if (smsNotificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SmsNotificationDTO result = smsNotificationService.save(smsNotificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, smsNotificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sms-notifications} : get all the smsNotifications.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of smsNotifications in body.
     */
    @GetMapping("/sms-notifications")
    public ResponseEntity<List<SmsNotificationDTO>> getAllSmsNotifications(Pageable pageable) {
        log.debug("REST request to get a page of SmsNotifications");
        Page<SmsNotificationDTO> page = smsNotificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sms-notifications/:id} : get the "id" smsNotification.
     *
     * @param id the id of the smsNotificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the smsNotificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sms-notifications/{id}")
    public ResponseEntity<SmsNotificationDTO> getSmsNotification(@PathVariable Long id) {
        log.debug("REST request to get SmsNotification : {}", id);
        Optional<SmsNotificationDTO> smsNotificationDTO = smsNotificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(smsNotificationDTO);
    }

    /**
     * {@code DELETE  /sms-notifications/:id} : delete the "id" smsNotification.
     *
     * @param id the id of the smsNotificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sms-notifications/{id}")
    public ResponseEntity<Void> deleteSmsNotification(@PathVariable Long id) {
        log.debug("REST request to delete SmsNotification : {}", id);

        smsNotificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
