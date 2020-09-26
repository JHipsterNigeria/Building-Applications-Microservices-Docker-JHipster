package com.jhispter.nigeria.web.rest;

import com.jhispter.nigeria.TollGateNotificationServiceApp;
import com.jhispter.nigeria.config.SecurityBeanOverrideConfiguration;
import com.jhispter.nigeria.domain.SmsNotification;
import com.jhispter.nigeria.repository.SmsNotificationRepository;
import com.jhispter.nigeria.service.SmsNotificationService;
import com.jhispter.nigeria.service.dto.SmsNotificationDTO;
import com.jhispter.nigeria.service.mapper.SmsNotificationMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhispter.nigeria.domain.enumeration.Status;
/**
 * Integration tests for the {@link SmsNotificationResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, TollGateNotificationServiceApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class SmsNotificationResourceIT {

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.INITIATED;
    private static final Status UPDATED_STATUS = Status.PROCESSING;

    private static final Instant DEFAULT_DATE_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_SENT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_SENT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private SmsNotificationRepository smsNotificationRepository;

    @Autowired
    private SmsNotificationMapper smsNotificationMapper;

    @Autowired
    private SmsNotificationService smsNotificationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSmsNotificationMockMvc;

    private SmsNotification smsNotification;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SmsNotification createEntity(EntityManager em) {
        SmsNotification smsNotification = new SmsNotification()
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .message(DEFAULT_MESSAGE)
            .status(DEFAULT_STATUS)
            .dateCreated(DEFAULT_DATE_CREATED)
            .dateSent(DEFAULT_DATE_SENT);
        return smsNotification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SmsNotification createUpdatedEntity(EntityManager em) {
        SmsNotification smsNotification = new SmsNotification()
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .message(UPDATED_MESSAGE)
            .status(UPDATED_STATUS)
            .dateCreated(UPDATED_DATE_CREATED)
            .dateSent(UPDATED_DATE_SENT);
        return smsNotification;
    }

    @BeforeEach
    public void initTest() {
        smsNotification = createEntity(em);
    }

    @Test
    @Transactional
    public void createSmsNotification() throws Exception {
        int databaseSizeBeforeCreate = smsNotificationRepository.findAll().size();
        // Create the SmsNotification
        SmsNotificationDTO smsNotificationDTO = smsNotificationMapper.toDto(smsNotification);
        restSmsNotificationMockMvc.perform(post("/api/sms-notifications").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(smsNotificationDTO)))
            .andExpect(status().isCreated());

        // Validate the SmsNotification in the database
        List<SmsNotification> smsNotificationList = smsNotificationRepository.findAll();
        assertThat(smsNotificationList).hasSize(databaseSizeBeforeCreate + 1);
        SmsNotification testSmsNotification = smsNotificationList.get(smsNotificationList.size() - 1);
        assertThat(testSmsNotification.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testSmsNotification.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testSmsNotification.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSmsNotification.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testSmsNotification.getDateSent()).isEqualTo(DEFAULT_DATE_SENT);
    }

    @Test
    @Transactional
    public void createSmsNotificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = smsNotificationRepository.findAll().size();

        // Create the SmsNotification with an existing ID
        smsNotification.setId(1L);
        SmsNotificationDTO smsNotificationDTO = smsNotificationMapper.toDto(smsNotification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSmsNotificationMockMvc.perform(post("/api/sms-notifications").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(smsNotificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SmsNotification in the database
        List<SmsNotification> smsNotificationList = smsNotificationRepository.findAll();
        assertThat(smsNotificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSmsNotifications() throws Exception {
        // Initialize the database
        smsNotificationRepository.saveAndFlush(smsNotification);

        // Get all the smsNotificationList
        restSmsNotificationMockMvc.perform(get("/api/sms-notifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(smsNotification.getId().intValue())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_DATE_CREATED.toString())))
            .andExpect(jsonPath("$.[*].dateSent").value(hasItem(DEFAULT_DATE_SENT.toString())));
    }
    
    @Test
    @Transactional
    public void getSmsNotification() throws Exception {
        // Initialize the database
        smsNotificationRepository.saveAndFlush(smsNotification);

        // Get the smsNotification
        restSmsNotificationMockMvc.perform(get("/api/sms-notifications/{id}", smsNotification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(smsNotification.getId().intValue()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.dateCreated").value(DEFAULT_DATE_CREATED.toString()))
            .andExpect(jsonPath("$.dateSent").value(DEFAULT_DATE_SENT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSmsNotification() throws Exception {
        // Get the smsNotification
        restSmsNotificationMockMvc.perform(get("/api/sms-notifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSmsNotification() throws Exception {
        // Initialize the database
        smsNotificationRepository.saveAndFlush(smsNotification);

        int databaseSizeBeforeUpdate = smsNotificationRepository.findAll().size();

        // Update the smsNotification
        SmsNotification updatedSmsNotification = smsNotificationRepository.findById(smsNotification.getId()).get();
        // Disconnect from session so that the updates on updatedSmsNotification are not directly saved in db
        em.detach(updatedSmsNotification);
        updatedSmsNotification
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .message(UPDATED_MESSAGE)
            .status(UPDATED_STATUS)
            .dateCreated(UPDATED_DATE_CREATED)
            .dateSent(UPDATED_DATE_SENT);
        SmsNotificationDTO smsNotificationDTO = smsNotificationMapper.toDto(updatedSmsNotification);

        restSmsNotificationMockMvc.perform(put("/api/sms-notifications").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(smsNotificationDTO)))
            .andExpect(status().isOk());

        // Validate the SmsNotification in the database
        List<SmsNotification> smsNotificationList = smsNotificationRepository.findAll();
        assertThat(smsNotificationList).hasSize(databaseSizeBeforeUpdate);
        SmsNotification testSmsNotification = smsNotificationList.get(smsNotificationList.size() - 1);
        assertThat(testSmsNotification.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testSmsNotification.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testSmsNotification.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSmsNotification.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testSmsNotification.getDateSent()).isEqualTo(UPDATED_DATE_SENT);
    }

    @Test
    @Transactional
    public void updateNonExistingSmsNotification() throws Exception {
        int databaseSizeBeforeUpdate = smsNotificationRepository.findAll().size();

        // Create the SmsNotification
        SmsNotificationDTO smsNotificationDTO = smsNotificationMapper.toDto(smsNotification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSmsNotificationMockMvc.perform(put("/api/sms-notifications").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(smsNotificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SmsNotification in the database
        List<SmsNotification> smsNotificationList = smsNotificationRepository.findAll();
        assertThat(smsNotificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSmsNotification() throws Exception {
        // Initialize the database
        smsNotificationRepository.saveAndFlush(smsNotification);

        int databaseSizeBeforeDelete = smsNotificationRepository.findAll().size();

        // Delete the smsNotification
        restSmsNotificationMockMvc.perform(delete("/api/sms-notifications/{id}", smsNotification.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SmsNotification> smsNotificationList = smsNotificationRepository.findAll();
        assertThat(smsNotificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
