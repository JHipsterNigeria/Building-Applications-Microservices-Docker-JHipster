package com.jhispter.nigeria.web.rest;

import com.jhispter.nigeria.TollGateNotificationServiceApp;
import com.jhispter.nigeria.config.SecurityBeanOverrideConfiguration;
import com.jhispter.nigeria.domain.EmailNotification;
import com.jhispter.nigeria.repository.EmailNotificationRepository;
import com.jhispter.nigeria.service.EmailNotificationService;
import com.jhispter.nigeria.service.dto.EmailNotificationDTO;
import com.jhispter.nigeria.service.mapper.EmailNotificationMapper;

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
 * Integration tests for the {@link EmailNotificationResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, TollGateNotificationServiceApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class EmailNotificationResourceIT {

    private static final String DEFAULT_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CC_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_CC_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.INITIATED;
    private static final Status UPDATED_STATUS = Status.PROCESSING;

    private static final Instant DEFAULT_DATE_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_SENT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_SENT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private EmailNotificationRepository emailNotificationRepository;

    @Autowired
    private EmailNotificationMapper emailNotificationMapper;

    @Autowired
    private EmailNotificationService emailNotificationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmailNotificationMockMvc;

    private EmailNotification emailNotification;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmailNotification createEntity(EntityManager em) {
        EmailNotification emailNotification = new EmailNotification()
            .emailAddress(DEFAULT_EMAIL_ADDRESS)
            .ccEmailAddress(DEFAULT_CC_EMAIL_ADDRESS)
            .subject(DEFAULT_SUBJECT)
            .status(DEFAULT_STATUS)
            .dateCreated(DEFAULT_DATE_CREATED)
            .dateSent(DEFAULT_DATE_SENT);
        return emailNotification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmailNotification createUpdatedEntity(EntityManager em) {
        EmailNotification emailNotification = new EmailNotification()
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .ccEmailAddress(UPDATED_CC_EMAIL_ADDRESS)
            .subject(UPDATED_SUBJECT)
            .status(UPDATED_STATUS)
            .dateCreated(UPDATED_DATE_CREATED)
            .dateSent(UPDATED_DATE_SENT);
        return emailNotification;
    }

    @BeforeEach
    public void initTest() {
        emailNotification = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmailNotification() throws Exception {
        int databaseSizeBeforeCreate = emailNotificationRepository.findAll().size();
        // Create the EmailNotification
        EmailNotificationDTO emailNotificationDTO = emailNotificationMapper.toDto(emailNotification);
        restEmailNotificationMockMvc.perform(post("/api/email-notifications").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emailNotificationDTO)))
            .andExpect(status().isCreated());

        // Validate the EmailNotification in the database
        List<EmailNotification> emailNotificationList = emailNotificationRepository.findAll();
        assertThat(emailNotificationList).hasSize(databaseSizeBeforeCreate + 1);
        EmailNotification testEmailNotification = emailNotificationList.get(emailNotificationList.size() - 1);
        assertThat(testEmailNotification.getEmailAddress()).isEqualTo(DEFAULT_EMAIL_ADDRESS);
        assertThat(testEmailNotification.getCcEmailAddress()).isEqualTo(DEFAULT_CC_EMAIL_ADDRESS);
        assertThat(testEmailNotification.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testEmailNotification.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEmailNotification.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testEmailNotification.getDateSent()).isEqualTo(DEFAULT_DATE_SENT);
    }

    @Test
    @Transactional
    public void createEmailNotificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emailNotificationRepository.findAll().size();

        // Create the EmailNotification with an existing ID
        emailNotification.setId(1L);
        EmailNotificationDTO emailNotificationDTO = emailNotificationMapper.toDto(emailNotification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmailNotificationMockMvc.perform(post("/api/email-notifications").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emailNotificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmailNotification in the database
        List<EmailNotification> emailNotificationList = emailNotificationRepository.findAll();
        assertThat(emailNotificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEmailNotifications() throws Exception {
        // Initialize the database
        emailNotificationRepository.saveAndFlush(emailNotification);

        // Get all the emailNotificationList
        restEmailNotificationMockMvc.perform(get("/api/email-notifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emailNotification.getId().intValue())))
            .andExpect(jsonPath("$.[*].emailAddress").value(hasItem(DEFAULT_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].ccEmailAddress").value(hasItem(DEFAULT_CC_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_DATE_CREATED.toString())))
            .andExpect(jsonPath("$.[*].dateSent").value(hasItem(DEFAULT_DATE_SENT.toString())));
    }
    
    @Test
    @Transactional
    public void getEmailNotification() throws Exception {
        // Initialize the database
        emailNotificationRepository.saveAndFlush(emailNotification);

        // Get the emailNotification
        restEmailNotificationMockMvc.perform(get("/api/email-notifications/{id}", emailNotification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(emailNotification.getId().intValue()))
            .andExpect(jsonPath("$.emailAddress").value(DEFAULT_EMAIL_ADDRESS))
            .andExpect(jsonPath("$.ccEmailAddress").value(DEFAULT_CC_EMAIL_ADDRESS))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.dateCreated").value(DEFAULT_DATE_CREATED.toString()))
            .andExpect(jsonPath("$.dateSent").value(DEFAULT_DATE_SENT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEmailNotification() throws Exception {
        // Get the emailNotification
        restEmailNotificationMockMvc.perform(get("/api/email-notifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmailNotification() throws Exception {
        // Initialize the database
        emailNotificationRepository.saveAndFlush(emailNotification);

        int databaseSizeBeforeUpdate = emailNotificationRepository.findAll().size();

        // Update the emailNotification
        EmailNotification updatedEmailNotification = emailNotificationRepository.findById(emailNotification.getId()).get();
        // Disconnect from session so that the updates on updatedEmailNotification are not directly saved in db
        em.detach(updatedEmailNotification);
        updatedEmailNotification
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .ccEmailAddress(UPDATED_CC_EMAIL_ADDRESS)
            .subject(UPDATED_SUBJECT)
            .status(UPDATED_STATUS)
            .dateCreated(UPDATED_DATE_CREATED)
            .dateSent(UPDATED_DATE_SENT);
        EmailNotificationDTO emailNotificationDTO = emailNotificationMapper.toDto(updatedEmailNotification);

        restEmailNotificationMockMvc.perform(put("/api/email-notifications").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emailNotificationDTO)))
            .andExpect(status().isOk());

        // Validate the EmailNotification in the database
        List<EmailNotification> emailNotificationList = emailNotificationRepository.findAll();
        assertThat(emailNotificationList).hasSize(databaseSizeBeforeUpdate);
        EmailNotification testEmailNotification = emailNotificationList.get(emailNotificationList.size() - 1);
        assertThat(testEmailNotification.getEmailAddress()).isEqualTo(UPDATED_EMAIL_ADDRESS);
        assertThat(testEmailNotification.getCcEmailAddress()).isEqualTo(UPDATED_CC_EMAIL_ADDRESS);
        assertThat(testEmailNotification.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testEmailNotification.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEmailNotification.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testEmailNotification.getDateSent()).isEqualTo(UPDATED_DATE_SENT);
    }

    @Test
    @Transactional
    public void updateNonExistingEmailNotification() throws Exception {
        int databaseSizeBeforeUpdate = emailNotificationRepository.findAll().size();

        // Create the EmailNotification
        EmailNotificationDTO emailNotificationDTO = emailNotificationMapper.toDto(emailNotification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmailNotificationMockMvc.perform(put("/api/email-notifications").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emailNotificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmailNotification in the database
        List<EmailNotification> emailNotificationList = emailNotificationRepository.findAll();
        assertThat(emailNotificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmailNotification() throws Exception {
        // Initialize the database
        emailNotificationRepository.saveAndFlush(emailNotification);

        int databaseSizeBeforeDelete = emailNotificationRepository.findAll().size();

        // Delete the emailNotification
        restEmailNotificationMockMvc.perform(delete("/api/email-notifications/{id}", emailNotification.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EmailNotification> emailNotificationList = emailNotificationRepository.findAll();
        assertThat(emailNotificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
