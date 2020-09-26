package com.jhipster.nigeria.web.rest;

import com.jhipster.nigeria.RedisTestContainerExtension;
import com.jhipster.nigeria.CoreserviceApp;
import com.jhipster.nigeria.config.SecurityBeanOverrideConfiguration;
import com.jhipster.nigeria.domain.Profile;
import com.jhipster.nigeria.repository.ProfileRepository;
import com.jhipster.nigeria.service.ProfileService;
import com.jhipster.nigeria.service.dto.ProfileDTO;
import com.jhipster.nigeria.service.mapper.ProfileMapper;
import com.jhipster.nigeria.service.dto.ProfileCriteria;
import com.jhipster.nigeria.service.ProfileQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProfileResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, CoreserviceApp.class })
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class ProfileResourceIT {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;
    private static final Long SMALLER_USER_ID = 1L - 1L;

    private static final String DEFAULT_LOGIN = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OPT_ACTIVATION_KEY = "AAAAAAAAAA";
    private static final String UPDATED_OPT_ACTIVATION_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVATION_KEY = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVATION_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_ALIAS = "AAAAAAAAAA";
    private static final String UPDATED_ALIAS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EMAIL_NOTIFICATIONS = false;
    private static final Boolean UPDATED_EMAIL_NOTIFICATIONS = true;

    private static final Boolean DEFAULT_SMS_NOTIFICATIONS = false;
    private static final Boolean UPDATED_SMS_NOTIFICATIONS = true;

    private static final byte[] DEFAULT_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_LANG = "AAAAAAAAAA";
    private static final String UPDATED_LANG = "BBBBBBBBBB";

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileMapper profileMapper;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileQueryService profileQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProfileMockMvc;

    private Profile profile;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Profile createEntity(EntityManager em) {
        Profile profile = new Profile()
            .userId(DEFAULT_USER_ID)
            .login(DEFAULT_LOGIN)
            .phone(DEFAULT_PHONE)
            .email(DEFAULT_EMAIL)
            .firstName(DEFAULT_FIRST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .optActivationKey(DEFAULT_OPT_ACTIVATION_KEY)
            .activationKey(DEFAULT_ACTIVATION_KEY)
            .alias(DEFAULT_ALIAS)
            .emailNotifications(DEFAULT_EMAIL_NOTIFICATIONS)
            .smsNotifications(DEFAULT_SMS_NOTIFICATIONS)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .lang(DEFAULT_LANG);
        return profile;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Profile createUpdatedEntity(EntityManager em) {
        Profile profile = new Profile()
            .userId(UPDATED_USER_ID)
            .login(UPDATED_LOGIN)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .optActivationKey(UPDATED_OPT_ACTIVATION_KEY)
            .activationKey(UPDATED_ACTIVATION_KEY)
            .alias(UPDATED_ALIAS)
            .emailNotifications(UPDATED_EMAIL_NOTIFICATIONS)
            .smsNotifications(UPDATED_SMS_NOTIFICATIONS)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .lang(UPDATED_LANG);
        return profile;
    }

    @BeforeEach
    public void initTest() {
        profile = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfile() throws Exception {
        int databaseSizeBeforeCreate = profileRepository.findAll().size();
        // Create the Profile
        ProfileDTO profileDTO = profileMapper.toDto(profile);
        restProfileMockMvc.perform(post("/api/profiles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
            .andExpect(status().isCreated());

        // Validate the Profile in the database
        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeCreate + 1);
        Profile testProfile = profileList.get(profileList.size() - 1);
        assertThat(testProfile.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testProfile.getLogin()).isEqualTo(DEFAULT_LOGIN);
        assertThat(testProfile.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testProfile.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testProfile.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testProfile.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testProfile.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testProfile.getOptActivationKey()).isEqualTo(DEFAULT_OPT_ACTIVATION_KEY);
        assertThat(testProfile.getActivationKey()).isEqualTo(DEFAULT_ACTIVATION_KEY);
        assertThat(testProfile.getAlias()).isEqualTo(DEFAULT_ALIAS);
        assertThat(testProfile.isEmailNotifications()).isEqualTo(DEFAULT_EMAIL_NOTIFICATIONS);
        assertThat(testProfile.isSmsNotifications()).isEqualTo(DEFAULT_SMS_NOTIFICATIONS);
        assertThat(testProfile.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testProfile.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testProfile.getLang()).isEqualTo(DEFAULT_LANG);
    }

    @Test
    @Transactional
    public void createProfileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profileRepository.findAll().size();

        // Create the Profile with an existing ID
        profile.setId(1L);
        ProfileDTO profileDTO = profileMapper.toDto(profile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfileMockMvc.perform(post("/api/profiles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Profile in the database
        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = profileRepository.findAll().size();
        // set the field null
        profile.setUserId(null);

        // Create the Profile, which fails.
        ProfileDTO profileDTO = profileMapper.toDto(profile);


        restProfileMockMvc.perform(post("/api/profiles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
            .andExpect(status().isBadRequest());

        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLoginIsRequired() throws Exception {
        int databaseSizeBeforeTest = profileRepository.findAll().size();
        // set the field null
        profile.setLogin(null);

        // Create the Profile, which fails.
        ProfileDTO profileDTO = profileMapper.toDto(profile);


        restProfileMockMvc.perform(post("/api/profiles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
            .andExpect(status().isBadRequest());

        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = profileRepository.findAll().size();
        // set the field null
        profile.setPhone(null);

        // Create the Profile, which fails.
        ProfileDTO profileDTO = profileMapper.toDto(profile);


        restProfileMockMvc.perform(post("/api/profiles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
            .andExpect(status().isBadRequest());

        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = profileRepository.findAll().size();
        // set the field null
        profile.setFirstName(null);

        // Create the Profile, which fails.
        ProfileDTO profileDTO = profileMapper.toDto(profile);


        restProfileMockMvc.perform(post("/api/profiles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
            .andExpect(status().isBadRequest());

        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = profileRepository.findAll().size();
        // set the field null
        profile.setLastName(null);

        // Create the Profile, which fails.
        ProfileDTO profileDTO = profileMapper.toDto(profile);


        restProfileMockMvc.perform(post("/api/profiles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
            .andExpect(status().isBadRequest());

        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLangIsRequired() throws Exception {
        int databaseSizeBeforeTest = profileRepository.findAll().size();
        // set the field null
        profile.setLang(null);

        // Create the Profile, which fails.
        ProfileDTO profileDTO = profileMapper.toDto(profile);


        restProfileMockMvc.perform(post("/api/profiles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
            .andExpect(status().isBadRequest());

        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProfiles() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList
        restProfileMockMvc.perform(get("/api/profiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profile.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].optActivationKey").value(hasItem(DEFAULT_OPT_ACTIVATION_KEY)))
            .andExpect(jsonPath("$.[*].activationKey").value(hasItem(DEFAULT_ACTIVATION_KEY)))
            .andExpect(jsonPath("$.[*].alias").value(hasItem(DEFAULT_ALIAS)))
            .andExpect(jsonPath("$.[*].emailNotifications").value(hasItem(DEFAULT_EMAIL_NOTIFICATIONS.booleanValue())))
            .andExpect(jsonPath("$.[*].smsNotifications").value(hasItem(DEFAULT_SMS_NOTIFICATIONS.booleanValue())))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].lang").value(hasItem(DEFAULT_LANG)));
    }
    
    @Test
    @Transactional
    public void getProfile() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get the profile
        restProfileMockMvc.perform(get("/api/profiles/{id}", profile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(profile.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.login").value(DEFAULT_LOGIN))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.optActivationKey").value(DEFAULT_OPT_ACTIVATION_KEY))
            .andExpect(jsonPath("$.activationKey").value(DEFAULT_ACTIVATION_KEY))
            .andExpect(jsonPath("$.alias").value(DEFAULT_ALIAS))
            .andExpect(jsonPath("$.emailNotifications").value(DEFAULT_EMAIL_NOTIFICATIONS.booleanValue()))
            .andExpect(jsonPath("$.smsNotifications").value(DEFAULT_SMS_NOTIFICATIONS.booleanValue()))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.lang").value(DEFAULT_LANG));
    }


    @Test
    @Transactional
    public void getProfilesByIdFiltering() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        Long id = profile.getId();

        defaultProfileShouldBeFound("id.equals=" + id);
        defaultProfileShouldNotBeFound("id.notEquals=" + id);

        defaultProfileShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultProfileShouldNotBeFound("id.greaterThan=" + id);

        defaultProfileShouldBeFound("id.lessThanOrEqual=" + id);
        defaultProfileShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllProfilesByUserIdIsEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where userId equals to DEFAULT_USER_ID
        defaultProfileShouldBeFound("userId.equals=" + DEFAULT_USER_ID);

        // Get all the profileList where userId equals to UPDATED_USER_ID
        defaultProfileShouldNotBeFound("userId.equals=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllProfilesByUserIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where userId not equals to DEFAULT_USER_ID
        defaultProfileShouldNotBeFound("userId.notEquals=" + DEFAULT_USER_ID);

        // Get all the profileList where userId not equals to UPDATED_USER_ID
        defaultProfileShouldBeFound("userId.notEquals=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllProfilesByUserIdIsInShouldWork() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where userId in DEFAULT_USER_ID or UPDATED_USER_ID
        defaultProfileShouldBeFound("userId.in=" + DEFAULT_USER_ID + "," + UPDATED_USER_ID);

        // Get all the profileList where userId equals to UPDATED_USER_ID
        defaultProfileShouldNotBeFound("userId.in=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllProfilesByUserIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where userId is not null
        defaultProfileShouldBeFound("userId.specified=true");

        // Get all the profileList where userId is null
        defaultProfileShouldNotBeFound("userId.specified=false");
    }

    @Test
    @Transactional
    public void getAllProfilesByUserIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where userId is greater than or equal to DEFAULT_USER_ID
        defaultProfileShouldBeFound("userId.greaterThanOrEqual=" + DEFAULT_USER_ID);

        // Get all the profileList where userId is greater than or equal to UPDATED_USER_ID
        defaultProfileShouldNotBeFound("userId.greaterThanOrEqual=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllProfilesByUserIdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where userId is less than or equal to DEFAULT_USER_ID
        defaultProfileShouldBeFound("userId.lessThanOrEqual=" + DEFAULT_USER_ID);

        // Get all the profileList where userId is less than or equal to SMALLER_USER_ID
        defaultProfileShouldNotBeFound("userId.lessThanOrEqual=" + SMALLER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllProfilesByUserIdIsLessThanSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where userId is less than DEFAULT_USER_ID
        defaultProfileShouldNotBeFound("userId.lessThan=" + DEFAULT_USER_ID);

        // Get all the profileList where userId is less than UPDATED_USER_ID
        defaultProfileShouldBeFound("userId.lessThan=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllProfilesByUserIdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where userId is greater than DEFAULT_USER_ID
        defaultProfileShouldNotBeFound("userId.greaterThan=" + DEFAULT_USER_ID);

        // Get all the profileList where userId is greater than SMALLER_USER_ID
        defaultProfileShouldBeFound("userId.greaterThan=" + SMALLER_USER_ID);
    }


    @Test
    @Transactional
    public void getAllProfilesByLoginIsEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where login equals to DEFAULT_LOGIN
        defaultProfileShouldBeFound("login.equals=" + DEFAULT_LOGIN);

        // Get all the profileList where login equals to UPDATED_LOGIN
        defaultProfileShouldNotBeFound("login.equals=" + UPDATED_LOGIN);
    }

    @Test
    @Transactional
    public void getAllProfilesByLoginIsNotEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where login not equals to DEFAULT_LOGIN
        defaultProfileShouldNotBeFound("login.notEquals=" + DEFAULT_LOGIN);

        // Get all the profileList where login not equals to UPDATED_LOGIN
        defaultProfileShouldBeFound("login.notEquals=" + UPDATED_LOGIN);
    }

    @Test
    @Transactional
    public void getAllProfilesByLoginIsInShouldWork() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where login in DEFAULT_LOGIN or UPDATED_LOGIN
        defaultProfileShouldBeFound("login.in=" + DEFAULT_LOGIN + "," + UPDATED_LOGIN);

        // Get all the profileList where login equals to UPDATED_LOGIN
        defaultProfileShouldNotBeFound("login.in=" + UPDATED_LOGIN);
    }

    @Test
    @Transactional
    public void getAllProfilesByLoginIsNullOrNotNull() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where login is not null
        defaultProfileShouldBeFound("login.specified=true");

        // Get all the profileList where login is null
        defaultProfileShouldNotBeFound("login.specified=false");
    }
                @Test
    @Transactional
    public void getAllProfilesByLoginContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where login contains DEFAULT_LOGIN
        defaultProfileShouldBeFound("login.contains=" + DEFAULT_LOGIN);

        // Get all the profileList where login contains UPDATED_LOGIN
        defaultProfileShouldNotBeFound("login.contains=" + UPDATED_LOGIN);
    }

    @Test
    @Transactional
    public void getAllProfilesByLoginNotContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where login does not contain DEFAULT_LOGIN
        defaultProfileShouldNotBeFound("login.doesNotContain=" + DEFAULT_LOGIN);

        // Get all the profileList where login does not contain UPDATED_LOGIN
        defaultProfileShouldBeFound("login.doesNotContain=" + UPDATED_LOGIN);
    }


    @Test
    @Transactional
    public void getAllProfilesByPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where phone equals to DEFAULT_PHONE
        defaultProfileShouldBeFound("phone.equals=" + DEFAULT_PHONE);

        // Get all the profileList where phone equals to UPDATED_PHONE
        defaultProfileShouldNotBeFound("phone.equals=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllProfilesByPhoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where phone not equals to DEFAULT_PHONE
        defaultProfileShouldNotBeFound("phone.notEquals=" + DEFAULT_PHONE);

        // Get all the profileList where phone not equals to UPDATED_PHONE
        defaultProfileShouldBeFound("phone.notEquals=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllProfilesByPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where phone in DEFAULT_PHONE or UPDATED_PHONE
        defaultProfileShouldBeFound("phone.in=" + DEFAULT_PHONE + "," + UPDATED_PHONE);

        // Get all the profileList where phone equals to UPDATED_PHONE
        defaultProfileShouldNotBeFound("phone.in=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllProfilesByPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where phone is not null
        defaultProfileShouldBeFound("phone.specified=true");

        // Get all the profileList where phone is null
        defaultProfileShouldNotBeFound("phone.specified=false");
    }
                @Test
    @Transactional
    public void getAllProfilesByPhoneContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where phone contains DEFAULT_PHONE
        defaultProfileShouldBeFound("phone.contains=" + DEFAULT_PHONE);

        // Get all the profileList where phone contains UPDATED_PHONE
        defaultProfileShouldNotBeFound("phone.contains=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllProfilesByPhoneNotContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where phone does not contain DEFAULT_PHONE
        defaultProfileShouldNotBeFound("phone.doesNotContain=" + DEFAULT_PHONE);

        // Get all the profileList where phone does not contain UPDATED_PHONE
        defaultProfileShouldBeFound("phone.doesNotContain=" + UPDATED_PHONE);
    }


    @Test
    @Transactional
    public void getAllProfilesByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where email equals to DEFAULT_EMAIL
        defaultProfileShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the profileList where email equals to UPDATED_EMAIL
        defaultProfileShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllProfilesByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where email not equals to DEFAULT_EMAIL
        defaultProfileShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the profileList where email not equals to UPDATED_EMAIL
        defaultProfileShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllProfilesByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultProfileShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the profileList where email equals to UPDATED_EMAIL
        defaultProfileShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllProfilesByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where email is not null
        defaultProfileShouldBeFound("email.specified=true");

        // Get all the profileList where email is null
        defaultProfileShouldNotBeFound("email.specified=false");
    }
                @Test
    @Transactional
    public void getAllProfilesByEmailContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where email contains DEFAULT_EMAIL
        defaultProfileShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the profileList where email contains UPDATED_EMAIL
        defaultProfileShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllProfilesByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where email does not contain DEFAULT_EMAIL
        defaultProfileShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the profileList where email does not contain UPDATED_EMAIL
        defaultProfileShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }


    @Test
    @Transactional
    public void getAllProfilesByFirstNameIsEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where firstName equals to DEFAULT_FIRST_NAME
        defaultProfileShouldBeFound("firstName.equals=" + DEFAULT_FIRST_NAME);

        // Get all the profileList where firstName equals to UPDATED_FIRST_NAME
        defaultProfileShouldNotBeFound("firstName.equals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllProfilesByFirstNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where firstName not equals to DEFAULT_FIRST_NAME
        defaultProfileShouldNotBeFound("firstName.notEquals=" + DEFAULT_FIRST_NAME);

        // Get all the profileList where firstName not equals to UPDATED_FIRST_NAME
        defaultProfileShouldBeFound("firstName.notEquals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllProfilesByFirstNameIsInShouldWork() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where firstName in DEFAULT_FIRST_NAME or UPDATED_FIRST_NAME
        defaultProfileShouldBeFound("firstName.in=" + DEFAULT_FIRST_NAME + "," + UPDATED_FIRST_NAME);

        // Get all the profileList where firstName equals to UPDATED_FIRST_NAME
        defaultProfileShouldNotBeFound("firstName.in=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllProfilesByFirstNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where firstName is not null
        defaultProfileShouldBeFound("firstName.specified=true");

        // Get all the profileList where firstName is null
        defaultProfileShouldNotBeFound("firstName.specified=false");
    }
                @Test
    @Transactional
    public void getAllProfilesByFirstNameContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where firstName contains DEFAULT_FIRST_NAME
        defaultProfileShouldBeFound("firstName.contains=" + DEFAULT_FIRST_NAME);

        // Get all the profileList where firstName contains UPDATED_FIRST_NAME
        defaultProfileShouldNotBeFound("firstName.contains=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllProfilesByFirstNameNotContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where firstName does not contain DEFAULT_FIRST_NAME
        defaultProfileShouldNotBeFound("firstName.doesNotContain=" + DEFAULT_FIRST_NAME);

        // Get all the profileList where firstName does not contain UPDATED_FIRST_NAME
        defaultProfileShouldBeFound("firstName.doesNotContain=" + UPDATED_FIRST_NAME);
    }


    @Test
    @Transactional
    public void getAllProfilesByMiddleNameIsEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where middleName equals to DEFAULT_MIDDLE_NAME
        defaultProfileShouldBeFound("middleName.equals=" + DEFAULT_MIDDLE_NAME);

        // Get all the profileList where middleName equals to UPDATED_MIDDLE_NAME
        defaultProfileShouldNotBeFound("middleName.equals=" + UPDATED_MIDDLE_NAME);
    }

    @Test
    @Transactional
    public void getAllProfilesByMiddleNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where middleName not equals to DEFAULT_MIDDLE_NAME
        defaultProfileShouldNotBeFound("middleName.notEquals=" + DEFAULT_MIDDLE_NAME);

        // Get all the profileList where middleName not equals to UPDATED_MIDDLE_NAME
        defaultProfileShouldBeFound("middleName.notEquals=" + UPDATED_MIDDLE_NAME);
    }

    @Test
    @Transactional
    public void getAllProfilesByMiddleNameIsInShouldWork() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where middleName in DEFAULT_MIDDLE_NAME or UPDATED_MIDDLE_NAME
        defaultProfileShouldBeFound("middleName.in=" + DEFAULT_MIDDLE_NAME + "," + UPDATED_MIDDLE_NAME);

        // Get all the profileList where middleName equals to UPDATED_MIDDLE_NAME
        defaultProfileShouldNotBeFound("middleName.in=" + UPDATED_MIDDLE_NAME);
    }

    @Test
    @Transactional
    public void getAllProfilesByMiddleNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where middleName is not null
        defaultProfileShouldBeFound("middleName.specified=true");

        // Get all the profileList where middleName is null
        defaultProfileShouldNotBeFound("middleName.specified=false");
    }
                @Test
    @Transactional
    public void getAllProfilesByMiddleNameContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where middleName contains DEFAULT_MIDDLE_NAME
        defaultProfileShouldBeFound("middleName.contains=" + DEFAULT_MIDDLE_NAME);

        // Get all the profileList where middleName contains UPDATED_MIDDLE_NAME
        defaultProfileShouldNotBeFound("middleName.contains=" + UPDATED_MIDDLE_NAME);
    }

    @Test
    @Transactional
    public void getAllProfilesByMiddleNameNotContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where middleName does not contain DEFAULT_MIDDLE_NAME
        defaultProfileShouldNotBeFound("middleName.doesNotContain=" + DEFAULT_MIDDLE_NAME);

        // Get all the profileList where middleName does not contain UPDATED_MIDDLE_NAME
        defaultProfileShouldBeFound("middleName.doesNotContain=" + UPDATED_MIDDLE_NAME);
    }


    @Test
    @Transactional
    public void getAllProfilesByLastNameIsEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where lastName equals to DEFAULT_LAST_NAME
        defaultProfileShouldBeFound("lastName.equals=" + DEFAULT_LAST_NAME);

        // Get all the profileList where lastName equals to UPDATED_LAST_NAME
        defaultProfileShouldNotBeFound("lastName.equals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllProfilesByLastNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where lastName not equals to DEFAULT_LAST_NAME
        defaultProfileShouldNotBeFound("lastName.notEquals=" + DEFAULT_LAST_NAME);

        // Get all the profileList where lastName not equals to UPDATED_LAST_NAME
        defaultProfileShouldBeFound("lastName.notEquals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllProfilesByLastNameIsInShouldWork() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where lastName in DEFAULT_LAST_NAME or UPDATED_LAST_NAME
        defaultProfileShouldBeFound("lastName.in=" + DEFAULT_LAST_NAME + "," + UPDATED_LAST_NAME);

        // Get all the profileList where lastName equals to UPDATED_LAST_NAME
        defaultProfileShouldNotBeFound("lastName.in=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllProfilesByLastNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where lastName is not null
        defaultProfileShouldBeFound("lastName.specified=true");

        // Get all the profileList where lastName is null
        defaultProfileShouldNotBeFound("lastName.specified=false");
    }
                @Test
    @Transactional
    public void getAllProfilesByLastNameContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where lastName contains DEFAULT_LAST_NAME
        defaultProfileShouldBeFound("lastName.contains=" + DEFAULT_LAST_NAME);

        // Get all the profileList where lastName contains UPDATED_LAST_NAME
        defaultProfileShouldNotBeFound("lastName.contains=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllProfilesByLastNameNotContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where lastName does not contain DEFAULT_LAST_NAME
        defaultProfileShouldNotBeFound("lastName.doesNotContain=" + DEFAULT_LAST_NAME);

        // Get all the profileList where lastName does not contain UPDATED_LAST_NAME
        defaultProfileShouldBeFound("lastName.doesNotContain=" + UPDATED_LAST_NAME);
    }


    @Test
    @Transactional
    public void getAllProfilesByOptActivationKeyIsEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where optActivationKey equals to DEFAULT_OPT_ACTIVATION_KEY
        defaultProfileShouldBeFound("optActivationKey.equals=" + DEFAULT_OPT_ACTIVATION_KEY);

        // Get all the profileList where optActivationKey equals to UPDATED_OPT_ACTIVATION_KEY
        defaultProfileShouldNotBeFound("optActivationKey.equals=" + UPDATED_OPT_ACTIVATION_KEY);
    }

    @Test
    @Transactional
    public void getAllProfilesByOptActivationKeyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where optActivationKey not equals to DEFAULT_OPT_ACTIVATION_KEY
        defaultProfileShouldNotBeFound("optActivationKey.notEquals=" + DEFAULT_OPT_ACTIVATION_KEY);

        // Get all the profileList where optActivationKey not equals to UPDATED_OPT_ACTIVATION_KEY
        defaultProfileShouldBeFound("optActivationKey.notEquals=" + UPDATED_OPT_ACTIVATION_KEY);
    }

    @Test
    @Transactional
    public void getAllProfilesByOptActivationKeyIsInShouldWork() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where optActivationKey in DEFAULT_OPT_ACTIVATION_KEY or UPDATED_OPT_ACTIVATION_KEY
        defaultProfileShouldBeFound("optActivationKey.in=" + DEFAULT_OPT_ACTIVATION_KEY + "," + UPDATED_OPT_ACTIVATION_KEY);

        // Get all the profileList where optActivationKey equals to UPDATED_OPT_ACTIVATION_KEY
        defaultProfileShouldNotBeFound("optActivationKey.in=" + UPDATED_OPT_ACTIVATION_KEY);
    }

    @Test
    @Transactional
    public void getAllProfilesByOptActivationKeyIsNullOrNotNull() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where optActivationKey is not null
        defaultProfileShouldBeFound("optActivationKey.specified=true");

        // Get all the profileList where optActivationKey is null
        defaultProfileShouldNotBeFound("optActivationKey.specified=false");
    }
                @Test
    @Transactional
    public void getAllProfilesByOptActivationKeyContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where optActivationKey contains DEFAULT_OPT_ACTIVATION_KEY
        defaultProfileShouldBeFound("optActivationKey.contains=" + DEFAULT_OPT_ACTIVATION_KEY);

        // Get all the profileList where optActivationKey contains UPDATED_OPT_ACTIVATION_KEY
        defaultProfileShouldNotBeFound("optActivationKey.contains=" + UPDATED_OPT_ACTIVATION_KEY);
    }

    @Test
    @Transactional
    public void getAllProfilesByOptActivationKeyNotContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where optActivationKey does not contain DEFAULT_OPT_ACTIVATION_KEY
        defaultProfileShouldNotBeFound("optActivationKey.doesNotContain=" + DEFAULT_OPT_ACTIVATION_KEY);

        // Get all the profileList where optActivationKey does not contain UPDATED_OPT_ACTIVATION_KEY
        defaultProfileShouldBeFound("optActivationKey.doesNotContain=" + UPDATED_OPT_ACTIVATION_KEY);
    }


    @Test
    @Transactional
    public void getAllProfilesByActivationKeyIsEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where activationKey equals to DEFAULT_ACTIVATION_KEY
        defaultProfileShouldBeFound("activationKey.equals=" + DEFAULT_ACTIVATION_KEY);

        // Get all the profileList where activationKey equals to UPDATED_ACTIVATION_KEY
        defaultProfileShouldNotBeFound("activationKey.equals=" + UPDATED_ACTIVATION_KEY);
    }

    @Test
    @Transactional
    public void getAllProfilesByActivationKeyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where activationKey not equals to DEFAULT_ACTIVATION_KEY
        defaultProfileShouldNotBeFound("activationKey.notEquals=" + DEFAULT_ACTIVATION_KEY);

        // Get all the profileList where activationKey not equals to UPDATED_ACTIVATION_KEY
        defaultProfileShouldBeFound("activationKey.notEquals=" + UPDATED_ACTIVATION_KEY);
    }

    @Test
    @Transactional
    public void getAllProfilesByActivationKeyIsInShouldWork() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where activationKey in DEFAULT_ACTIVATION_KEY or UPDATED_ACTIVATION_KEY
        defaultProfileShouldBeFound("activationKey.in=" + DEFAULT_ACTIVATION_KEY + "," + UPDATED_ACTIVATION_KEY);

        // Get all the profileList where activationKey equals to UPDATED_ACTIVATION_KEY
        defaultProfileShouldNotBeFound("activationKey.in=" + UPDATED_ACTIVATION_KEY);
    }

    @Test
    @Transactional
    public void getAllProfilesByActivationKeyIsNullOrNotNull() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where activationKey is not null
        defaultProfileShouldBeFound("activationKey.specified=true");

        // Get all the profileList where activationKey is null
        defaultProfileShouldNotBeFound("activationKey.specified=false");
    }
                @Test
    @Transactional
    public void getAllProfilesByActivationKeyContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where activationKey contains DEFAULT_ACTIVATION_KEY
        defaultProfileShouldBeFound("activationKey.contains=" + DEFAULT_ACTIVATION_KEY);

        // Get all the profileList where activationKey contains UPDATED_ACTIVATION_KEY
        defaultProfileShouldNotBeFound("activationKey.contains=" + UPDATED_ACTIVATION_KEY);
    }

    @Test
    @Transactional
    public void getAllProfilesByActivationKeyNotContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where activationKey does not contain DEFAULT_ACTIVATION_KEY
        defaultProfileShouldNotBeFound("activationKey.doesNotContain=" + DEFAULT_ACTIVATION_KEY);

        // Get all the profileList where activationKey does not contain UPDATED_ACTIVATION_KEY
        defaultProfileShouldBeFound("activationKey.doesNotContain=" + UPDATED_ACTIVATION_KEY);
    }


    @Test
    @Transactional
    public void getAllProfilesByAliasIsEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where alias equals to DEFAULT_ALIAS
        defaultProfileShouldBeFound("alias.equals=" + DEFAULT_ALIAS);

        // Get all the profileList where alias equals to UPDATED_ALIAS
        defaultProfileShouldNotBeFound("alias.equals=" + UPDATED_ALIAS);
    }

    @Test
    @Transactional
    public void getAllProfilesByAliasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where alias not equals to DEFAULT_ALIAS
        defaultProfileShouldNotBeFound("alias.notEquals=" + DEFAULT_ALIAS);

        // Get all the profileList where alias not equals to UPDATED_ALIAS
        defaultProfileShouldBeFound("alias.notEquals=" + UPDATED_ALIAS);
    }

    @Test
    @Transactional
    public void getAllProfilesByAliasIsInShouldWork() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where alias in DEFAULT_ALIAS or UPDATED_ALIAS
        defaultProfileShouldBeFound("alias.in=" + DEFAULT_ALIAS + "," + UPDATED_ALIAS);

        // Get all the profileList where alias equals to UPDATED_ALIAS
        defaultProfileShouldNotBeFound("alias.in=" + UPDATED_ALIAS);
    }

    @Test
    @Transactional
    public void getAllProfilesByAliasIsNullOrNotNull() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where alias is not null
        defaultProfileShouldBeFound("alias.specified=true");

        // Get all the profileList where alias is null
        defaultProfileShouldNotBeFound("alias.specified=false");
    }
                @Test
    @Transactional
    public void getAllProfilesByAliasContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where alias contains DEFAULT_ALIAS
        defaultProfileShouldBeFound("alias.contains=" + DEFAULT_ALIAS);

        // Get all the profileList where alias contains UPDATED_ALIAS
        defaultProfileShouldNotBeFound("alias.contains=" + UPDATED_ALIAS);
    }

    @Test
    @Transactional
    public void getAllProfilesByAliasNotContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where alias does not contain DEFAULT_ALIAS
        defaultProfileShouldNotBeFound("alias.doesNotContain=" + DEFAULT_ALIAS);

        // Get all the profileList where alias does not contain UPDATED_ALIAS
        defaultProfileShouldBeFound("alias.doesNotContain=" + UPDATED_ALIAS);
    }


    @Test
    @Transactional
    public void getAllProfilesByEmailNotificationsIsEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where emailNotifications equals to DEFAULT_EMAIL_NOTIFICATIONS
        defaultProfileShouldBeFound("emailNotifications.equals=" + DEFAULT_EMAIL_NOTIFICATIONS);

        // Get all the profileList where emailNotifications equals to UPDATED_EMAIL_NOTIFICATIONS
        defaultProfileShouldNotBeFound("emailNotifications.equals=" + UPDATED_EMAIL_NOTIFICATIONS);
    }

    @Test
    @Transactional
    public void getAllProfilesByEmailNotificationsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where emailNotifications not equals to DEFAULT_EMAIL_NOTIFICATIONS
        defaultProfileShouldNotBeFound("emailNotifications.notEquals=" + DEFAULT_EMAIL_NOTIFICATIONS);

        // Get all the profileList where emailNotifications not equals to UPDATED_EMAIL_NOTIFICATIONS
        defaultProfileShouldBeFound("emailNotifications.notEquals=" + UPDATED_EMAIL_NOTIFICATIONS);
    }

    @Test
    @Transactional
    public void getAllProfilesByEmailNotificationsIsInShouldWork() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where emailNotifications in DEFAULT_EMAIL_NOTIFICATIONS or UPDATED_EMAIL_NOTIFICATIONS
        defaultProfileShouldBeFound("emailNotifications.in=" + DEFAULT_EMAIL_NOTIFICATIONS + "," + UPDATED_EMAIL_NOTIFICATIONS);

        // Get all the profileList where emailNotifications equals to UPDATED_EMAIL_NOTIFICATIONS
        defaultProfileShouldNotBeFound("emailNotifications.in=" + UPDATED_EMAIL_NOTIFICATIONS);
    }

    @Test
    @Transactional
    public void getAllProfilesByEmailNotificationsIsNullOrNotNull() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where emailNotifications is not null
        defaultProfileShouldBeFound("emailNotifications.specified=true");

        // Get all the profileList where emailNotifications is null
        defaultProfileShouldNotBeFound("emailNotifications.specified=false");
    }

    @Test
    @Transactional
    public void getAllProfilesBySmsNotificationsIsEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where smsNotifications equals to DEFAULT_SMS_NOTIFICATIONS
        defaultProfileShouldBeFound("smsNotifications.equals=" + DEFAULT_SMS_NOTIFICATIONS);

        // Get all the profileList where smsNotifications equals to UPDATED_SMS_NOTIFICATIONS
        defaultProfileShouldNotBeFound("smsNotifications.equals=" + UPDATED_SMS_NOTIFICATIONS);
    }

    @Test
    @Transactional
    public void getAllProfilesBySmsNotificationsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where smsNotifications not equals to DEFAULT_SMS_NOTIFICATIONS
        defaultProfileShouldNotBeFound("smsNotifications.notEquals=" + DEFAULT_SMS_NOTIFICATIONS);

        // Get all the profileList where smsNotifications not equals to UPDATED_SMS_NOTIFICATIONS
        defaultProfileShouldBeFound("smsNotifications.notEquals=" + UPDATED_SMS_NOTIFICATIONS);
    }

    @Test
    @Transactional
    public void getAllProfilesBySmsNotificationsIsInShouldWork() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where smsNotifications in DEFAULT_SMS_NOTIFICATIONS or UPDATED_SMS_NOTIFICATIONS
        defaultProfileShouldBeFound("smsNotifications.in=" + DEFAULT_SMS_NOTIFICATIONS + "," + UPDATED_SMS_NOTIFICATIONS);

        // Get all the profileList where smsNotifications equals to UPDATED_SMS_NOTIFICATIONS
        defaultProfileShouldNotBeFound("smsNotifications.in=" + UPDATED_SMS_NOTIFICATIONS);
    }

    @Test
    @Transactional
    public void getAllProfilesBySmsNotificationsIsNullOrNotNull() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where smsNotifications is not null
        defaultProfileShouldBeFound("smsNotifications.specified=true");

        // Get all the profileList where smsNotifications is null
        defaultProfileShouldNotBeFound("smsNotifications.specified=false");
    }

    @Test
    @Transactional
    public void getAllProfilesByLangIsEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where lang equals to DEFAULT_LANG
        defaultProfileShouldBeFound("lang.equals=" + DEFAULT_LANG);

        // Get all the profileList where lang equals to UPDATED_LANG
        defaultProfileShouldNotBeFound("lang.equals=" + UPDATED_LANG);
    }

    @Test
    @Transactional
    public void getAllProfilesByLangIsNotEqualToSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where lang not equals to DEFAULT_LANG
        defaultProfileShouldNotBeFound("lang.notEquals=" + DEFAULT_LANG);

        // Get all the profileList where lang not equals to UPDATED_LANG
        defaultProfileShouldBeFound("lang.notEquals=" + UPDATED_LANG);
    }

    @Test
    @Transactional
    public void getAllProfilesByLangIsInShouldWork() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where lang in DEFAULT_LANG or UPDATED_LANG
        defaultProfileShouldBeFound("lang.in=" + DEFAULT_LANG + "," + UPDATED_LANG);

        // Get all the profileList where lang equals to UPDATED_LANG
        defaultProfileShouldNotBeFound("lang.in=" + UPDATED_LANG);
    }

    @Test
    @Transactional
    public void getAllProfilesByLangIsNullOrNotNull() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where lang is not null
        defaultProfileShouldBeFound("lang.specified=true");

        // Get all the profileList where lang is null
        defaultProfileShouldNotBeFound("lang.specified=false");
    }
                @Test
    @Transactional
    public void getAllProfilesByLangContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where lang contains DEFAULT_LANG
        defaultProfileShouldBeFound("lang.contains=" + DEFAULT_LANG);

        // Get all the profileList where lang contains UPDATED_LANG
        defaultProfileShouldNotBeFound("lang.contains=" + UPDATED_LANG);
    }

    @Test
    @Transactional
    public void getAllProfilesByLangNotContainsSomething() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList where lang does not contain DEFAULT_LANG
        defaultProfileShouldNotBeFound("lang.doesNotContain=" + DEFAULT_LANG);

        // Get all the profileList where lang does not contain UPDATED_LANG
        defaultProfileShouldBeFound("lang.doesNotContain=" + UPDATED_LANG);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultProfileShouldBeFound(String filter) throws Exception {
        restProfileMockMvc.perform(get("/api/profiles?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profile.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].optActivationKey").value(hasItem(DEFAULT_OPT_ACTIVATION_KEY)))
            .andExpect(jsonPath("$.[*].activationKey").value(hasItem(DEFAULT_ACTIVATION_KEY)))
            .andExpect(jsonPath("$.[*].alias").value(hasItem(DEFAULT_ALIAS)))
            .andExpect(jsonPath("$.[*].emailNotifications").value(hasItem(DEFAULT_EMAIL_NOTIFICATIONS.booleanValue())))
            .andExpect(jsonPath("$.[*].smsNotifications").value(hasItem(DEFAULT_SMS_NOTIFICATIONS.booleanValue())))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].lang").value(hasItem(DEFAULT_LANG)));

        // Check, that the count call also returns 1
        restProfileMockMvc.perform(get("/api/profiles/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultProfileShouldNotBeFound(String filter) throws Exception {
        restProfileMockMvc.perform(get("/api/profiles?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restProfileMockMvc.perform(get("/api/profiles/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingProfile() throws Exception {
        // Get the profile
        restProfileMockMvc.perform(get("/api/profiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfile() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        int databaseSizeBeforeUpdate = profileRepository.findAll().size();

        // Update the profile
        Profile updatedProfile = profileRepository.findById(profile.getId()).get();
        // Disconnect from session so that the updates on updatedProfile are not directly saved in db
        em.detach(updatedProfile);
        updatedProfile
            .userId(UPDATED_USER_ID)
            .login(UPDATED_LOGIN)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .optActivationKey(UPDATED_OPT_ACTIVATION_KEY)
            .activationKey(UPDATED_ACTIVATION_KEY)
            .alias(UPDATED_ALIAS)
            .emailNotifications(UPDATED_EMAIL_NOTIFICATIONS)
            .smsNotifications(UPDATED_SMS_NOTIFICATIONS)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .lang(UPDATED_LANG);
        ProfileDTO profileDTO = profileMapper.toDto(updatedProfile);

        restProfileMockMvc.perform(put("/api/profiles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
            .andExpect(status().isOk());

        // Validate the Profile in the database
        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeUpdate);
        Profile testProfile = profileList.get(profileList.size() - 1);
        assertThat(testProfile.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testProfile.getLogin()).isEqualTo(UPDATED_LOGIN);
        assertThat(testProfile.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testProfile.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testProfile.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testProfile.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testProfile.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testProfile.getOptActivationKey()).isEqualTo(UPDATED_OPT_ACTIVATION_KEY);
        assertThat(testProfile.getActivationKey()).isEqualTo(UPDATED_ACTIVATION_KEY);
        assertThat(testProfile.getAlias()).isEqualTo(UPDATED_ALIAS);
        assertThat(testProfile.isEmailNotifications()).isEqualTo(UPDATED_EMAIL_NOTIFICATIONS);
        assertThat(testProfile.isSmsNotifications()).isEqualTo(UPDATED_SMS_NOTIFICATIONS);
        assertThat(testProfile.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testProfile.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testProfile.getLang()).isEqualTo(UPDATED_LANG);
    }

    @Test
    @Transactional
    public void updateNonExistingProfile() throws Exception {
        int databaseSizeBeforeUpdate = profileRepository.findAll().size();

        // Create the Profile
        ProfileDTO profileDTO = profileMapper.toDto(profile);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfileMockMvc.perform(put("/api/profiles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Profile in the database
        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProfile() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        int databaseSizeBeforeDelete = profileRepository.findAll().size();

        // Delete the profile
        restProfileMockMvc.perform(delete("/api/profiles/{id}", profile.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
