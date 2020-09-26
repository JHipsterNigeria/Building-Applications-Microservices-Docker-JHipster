package com.jhipster.nigeria.web.rest;

import com.jhipster.nigeria.RedisTestContainerExtension;
import com.jhipster.nigeria.CoreserviceApp;
import com.jhipster.nigeria.config.SecurityBeanOverrideConfiguration;
import com.jhipster.nigeria.domain.TransactionRequest;
import com.jhipster.nigeria.domain.VirtualAccount;
import com.jhipster.nigeria.repository.TransactionRequestRepository;
import com.jhipster.nigeria.service.TransactionRequestService;
import com.jhipster.nigeria.service.dto.TransactionRequestDTO;
import com.jhipster.nigeria.service.mapper.TransactionRequestMapper;
import com.jhipster.nigeria.service.dto.TransactionRequestCriteria;
import com.jhipster.nigeria.service.TransactionRequestQueryService;

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
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.nigeria.domain.enumeration.TransactionType;
/**
 * Integration tests for the {@link TransactionRequestResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, CoreserviceApp.class })
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class TransactionRequestResourceIT {

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);
    private static final BigDecimal SMALLER_AMOUNT = new BigDecimal(1 - 1);

    private static final String DEFAULT_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_ACCOUNT_BANK_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ACCOUNT_BANK_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_NARRATION = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_NARRATION = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION_ACCOUNT_BANK_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_ACCOUNT_BANK_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION_NARRATION = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_NARRATION = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_WEB_HOOK = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_WEB_HOOK = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_REF = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_REF = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_MESSAGE = "BBBBBBBBBB";

    private static final TransactionType DEFAULT_TRANSACTION_TYPE = TransactionType.WALLET_TOPUP;
    private static final TransactionType UPDATED_TRANSACTION_TYPE = TransactionType.BILLS_PAYMENT;

    private static final String DEFAULT_SCHEME_OWNER_ID = "AAAAAAAAAA";
    private static final String UPDATED_SCHEME_OWNER_ID = "BBBBBBBBBB";

    @Autowired
    private TransactionRequestRepository transactionRequestRepository;

    @Autowired
    private TransactionRequestMapper transactionRequestMapper;

    @Autowired
    private TransactionRequestService transactionRequestService;

    @Autowired
    private TransactionRequestQueryService transactionRequestQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTransactionRequestMockMvc;

    private TransactionRequest transactionRequest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransactionRequest createEntity(EntityManager em) {
        TransactionRequest transactionRequest = new TransactionRequest()
            .amount(DEFAULT_AMOUNT)
            .channel(DEFAULT_CHANNEL)
            .currency(DEFAULT_CURRENCY)
            .sourceAccount(DEFAULT_SOURCE_ACCOUNT)
            .sourceAccountBankCode(DEFAULT_SOURCE_ACCOUNT_BANK_CODE)
            .sourceAccountName(DEFAULT_SOURCE_ACCOUNT_NAME)
            .sourceNarration(DEFAULT_SOURCE_NARRATION)
            .destinationAccount(DEFAULT_DESTINATION_ACCOUNT)
            .destinationAccountBankCode(DEFAULT_DESTINATION_ACCOUNT_BANK_CODE)
            .destinationAccountName(DEFAULT_DESTINATION_ACCOUNT_NAME)
            .destinationNarration(DEFAULT_DESTINATION_NARRATION)
            .statusWebHook(DEFAULT_STATUS_WEB_HOOK)
            .transactionRef(DEFAULT_TRANSACTION_REF)
            .responseCode(DEFAULT_RESPONSE_CODE)
            .responseMessage(DEFAULT_RESPONSE_MESSAGE)
            .transactionType(DEFAULT_TRANSACTION_TYPE)
            .schemeOwnerId(DEFAULT_SCHEME_OWNER_ID);
        return transactionRequest;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransactionRequest createUpdatedEntity(EntityManager em) {
        TransactionRequest transactionRequest = new TransactionRequest()
            .amount(UPDATED_AMOUNT)
            .channel(UPDATED_CHANNEL)
            .currency(UPDATED_CURRENCY)
            .sourceAccount(UPDATED_SOURCE_ACCOUNT)
            .sourceAccountBankCode(UPDATED_SOURCE_ACCOUNT_BANK_CODE)
            .sourceAccountName(UPDATED_SOURCE_ACCOUNT_NAME)
            .sourceNarration(UPDATED_SOURCE_NARRATION)
            .destinationAccount(UPDATED_DESTINATION_ACCOUNT)
            .destinationAccountBankCode(UPDATED_DESTINATION_ACCOUNT_BANK_CODE)
            .destinationAccountName(UPDATED_DESTINATION_ACCOUNT_NAME)
            .destinationNarration(UPDATED_DESTINATION_NARRATION)
            .statusWebHook(UPDATED_STATUS_WEB_HOOK)
            .transactionRef(UPDATED_TRANSACTION_REF)
            .responseCode(UPDATED_RESPONSE_CODE)
            .responseMessage(UPDATED_RESPONSE_MESSAGE)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .schemeOwnerId(UPDATED_SCHEME_OWNER_ID);
        return transactionRequest;
    }

    @BeforeEach
    public void initTest() {
        transactionRequest = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransactionRequest() throws Exception {
        int databaseSizeBeforeCreate = transactionRequestRepository.findAll().size();
        // Create the TransactionRequest
        TransactionRequestDTO transactionRequestDTO = transactionRequestMapper.toDto(transactionRequest);
        restTransactionRequestMockMvc.perform(post("/api/transaction-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionRequestDTO)))
            .andExpect(status().isCreated());

        // Validate the TransactionRequest in the database
        List<TransactionRequest> transactionRequestList = transactionRequestRepository.findAll();
        assertThat(transactionRequestList).hasSize(databaseSizeBeforeCreate + 1);
        TransactionRequest testTransactionRequest = transactionRequestList.get(transactionRequestList.size() - 1);
        assertThat(testTransactionRequest.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testTransactionRequest.getChannel()).isEqualTo(DEFAULT_CHANNEL);
        assertThat(testTransactionRequest.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testTransactionRequest.getSourceAccount()).isEqualTo(DEFAULT_SOURCE_ACCOUNT);
        assertThat(testTransactionRequest.getSourceAccountBankCode()).isEqualTo(DEFAULT_SOURCE_ACCOUNT_BANK_CODE);
        assertThat(testTransactionRequest.getSourceAccountName()).isEqualTo(DEFAULT_SOURCE_ACCOUNT_NAME);
        assertThat(testTransactionRequest.getSourceNarration()).isEqualTo(DEFAULT_SOURCE_NARRATION);
        assertThat(testTransactionRequest.getDestinationAccount()).isEqualTo(DEFAULT_DESTINATION_ACCOUNT);
        assertThat(testTransactionRequest.getDestinationAccountBankCode()).isEqualTo(DEFAULT_DESTINATION_ACCOUNT_BANK_CODE);
        assertThat(testTransactionRequest.getDestinationAccountName()).isEqualTo(DEFAULT_DESTINATION_ACCOUNT_NAME);
        assertThat(testTransactionRequest.getDestinationNarration()).isEqualTo(DEFAULT_DESTINATION_NARRATION);
        assertThat(testTransactionRequest.getStatusWebHook()).isEqualTo(DEFAULT_STATUS_WEB_HOOK);
        assertThat(testTransactionRequest.getTransactionRef()).isEqualTo(DEFAULT_TRANSACTION_REF);
        assertThat(testTransactionRequest.getResponseCode()).isEqualTo(DEFAULT_RESPONSE_CODE);
        assertThat(testTransactionRequest.getResponseMessage()).isEqualTo(DEFAULT_RESPONSE_MESSAGE);
        assertThat(testTransactionRequest.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testTransactionRequest.getSchemeOwnerId()).isEqualTo(DEFAULT_SCHEME_OWNER_ID);
    }

    @Test
    @Transactional
    public void createTransactionRequestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transactionRequestRepository.findAll().size();

        // Create the TransactionRequest with an existing ID
        transactionRequest.setId(1L);
        TransactionRequestDTO transactionRequestDTO = transactionRequestMapper.toDto(transactionRequest);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransactionRequestMockMvc.perform(post("/api/transaction-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionRequestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TransactionRequest in the database
        List<TransactionRequest> transactionRequestList = transactionRequestRepository.findAll();
        assertThat(transactionRequestList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRequestRepository.findAll().size();
        // set the field null
        transactionRequest.setAmount(null);

        // Create the TransactionRequest, which fails.
        TransactionRequestDTO transactionRequestDTO = transactionRequestMapper.toDto(transactionRequest);


        restTransactionRequestMockMvc.perform(post("/api/transaction-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionRequestDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionRequest> transactionRequestList = transactionRequestRepository.findAll();
        assertThat(transactionRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkChannelIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRequestRepository.findAll().size();
        // set the field null
        transactionRequest.setChannel(null);

        // Create the TransactionRequest, which fails.
        TransactionRequestDTO transactionRequestDTO = transactionRequestMapper.toDto(transactionRequest);


        restTransactionRequestMockMvc.perform(post("/api/transaction-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionRequestDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionRequest> transactionRequestList = transactionRequestRepository.findAll();
        assertThat(transactionRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRequestRepository.findAll().size();
        // set the field null
        transactionRequest.setCurrency(null);

        // Create the TransactionRequest, which fails.
        TransactionRequestDTO transactionRequestDTO = transactionRequestMapper.toDto(transactionRequest);


        restTransactionRequestMockMvc.perform(post("/api/transaction-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionRequestDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionRequest> transactionRequestList = transactionRequestRepository.findAll();
        assertThat(transactionRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSourceAccountIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRequestRepository.findAll().size();
        // set the field null
        transactionRequest.setSourceAccount(null);

        // Create the TransactionRequest, which fails.
        TransactionRequestDTO transactionRequestDTO = transactionRequestMapper.toDto(transactionRequest);


        restTransactionRequestMockMvc.perform(post("/api/transaction-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionRequestDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionRequest> transactionRequestList = transactionRequestRepository.findAll();
        assertThat(transactionRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSourceAccountBankCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRequestRepository.findAll().size();
        // set the field null
        transactionRequest.setSourceAccountBankCode(null);

        // Create the TransactionRequest, which fails.
        TransactionRequestDTO transactionRequestDTO = transactionRequestMapper.toDto(transactionRequest);


        restTransactionRequestMockMvc.perform(post("/api/transaction-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionRequestDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionRequest> transactionRequestList = transactionRequestRepository.findAll();
        assertThat(transactionRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSourceNarrationIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRequestRepository.findAll().size();
        // set the field null
        transactionRequest.setSourceNarration(null);

        // Create the TransactionRequest, which fails.
        TransactionRequestDTO transactionRequestDTO = transactionRequestMapper.toDto(transactionRequest);


        restTransactionRequestMockMvc.perform(post("/api/transaction-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionRequestDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionRequest> transactionRequestList = transactionRequestRepository.findAll();
        assertThat(transactionRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDestinationAccountIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRequestRepository.findAll().size();
        // set the field null
        transactionRequest.setDestinationAccount(null);

        // Create the TransactionRequest, which fails.
        TransactionRequestDTO transactionRequestDTO = transactionRequestMapper.toDto(transactionRequest);


        restTransactionRequestMockMvc.perform(post("/api/transaction-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionRequestDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionRequest> transactionRequestList = transactionRequestRepository.findAll();
        assertThat(transactionRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDestinationAccountBankCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRequestRepository.findAll().size();
        // set the field null
        transactionRequest.setDestinationAccountBankCode(null);

        // Create the TransactionRequest, which fails.
        TransactionRequestDTO transactionRequestDTO = transactionRequestMapper.toDto(transactionRequest);


        restTransactionRequestMockMvc.perform(post("/api/transaction-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionRequestDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionRequest> transactionRequestList = transactionRequestRepository.findAll();
        assertThat(transactionRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDestinationNarrationIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRequestRepository.findAll().size();
        // set the field null
        transactionRequest.setDestinationNarration(null);

        // Create the TransactionRequest, which fails.
        TransactionRequestDTO transactionRequestDTO = transactionRequestMapper.toDto(transactionRequest);


        restTransactionRequestMockMvc.perform(post("/api/transaction-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionRequestDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionRequest> transactionRequestList = transactionRequestRepository.findAll();
        assertThat(transactionRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTransactionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRequestRepository.findAll().size();
        // set the field null
        transactionRequest.setTransactionType(null);

        // Create the TransactionRequest, which fails.
        TransactionRequestDTO transactionRequestDTO = transactionRequestMapper.toDto(transactionRequest);


        restTransactionRequestMockMvc.perform(post("/api/transaction-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionRequestDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionRequest> transactionRequestList = transactionRequestRepository.findAll();
        assertThat(transactionRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSchemeOwnerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRequestRepository.findAll().size();
        // set the field null
        transactionRequest.setSchemeOwnerId(null);

        // Create the TransactionRequest, which fails.
        TransactionRequestDTO transactionRequestDTO = transactionRequestMapper.toDto(transactionRequest);


        restTransactionRequestMockMvc.perform(post("/api/transaction-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionRequestDTO)))
            .andExpect(status().isBadRequest());

        List<TransactionRequest> transactionRequestList = transactionRequestRepository.findAll();
        assertThat(transactionRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTransactionRequests() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList
        restTransactionRequestMockMvc.perform(get("/api/transaction-requests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transactionRequest.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].channel").value(hasItem(DEFAULT_CHANNEL)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].sourceAccount").value(hasItem(DEFAULT_SOURCE_ACCOUNT)))
            .andExpect(jsonPath("$.[*].sourceAccountBankCode").value(hasItem(DEFAULT_SOURCE_ACCOUNT_BANK_CODE)))
            .andExpect(jsonPath("$.[*].sourceAccountName").value(hasItem(DEFAULT_SOURCE_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].sourceNarration").value(hasItem(DEFAULT_SOURCE_NARRATION)))
            .andExpect(jsonPath("$.[*].destinationAccount").value(hasItem(DEFAULT_DESTINATION_ACCOUNT)))
            .andExpect(jsonPath("$.[*].destinationAccountBankCode").value(hasItem(DEFAULT_DESTINATION_ACCOUNT_BANK_CODE)))
            .andExpect(jsonPath("$.[*].destinationAccountName").value(hasItem(DEFAULT_DESTINATION_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].destinationNarration").value(hasItem(DEFAULT_DESTINATION_NARRATION)))
            .andExpect(jsonPath("$.[*].statusWebHook").value(hasItem(DEFAULT_STATUS_WEB_HOOK)))
            .andExpect(jsonPath("$.[*].transactionRef").value(hasItem(DEFAULT_TRANSACTION_REF)))
            .andExpect(jsonPath("$.[*].responseCode").value(hasItem(DEFAULT_RESPONSE_CODE)))
            .andExpect(jsonPath("$.[*].responseMessage").value(hasItem(DEFAULT_RESPONSE_MESSAGE)))
            .andExpect(jsonPath("$.[*].transactionType").value(hasItem(DEFAULT_TRANSACTION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].schemeOwnerId").value(hasItem(DEFAULT_SCHEME_OWNER_ID)));
    }
    
    @Test
    @Transactional
    public void getTransactionRequest() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get the transactionRequest
        restTransactionRequestMockMvc.perform(get("/api/transaction-requests/{id}", transactionRequest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transactionRequest.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.channel").value(DEFAULT_CHANNEL))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY))
            .andExpect(jsonPath("$.sourceAccount").value(DEFAULT_SOURCE_ACCOUNT))
            .andExpect(jsonPath("$.sourceAccountBankCode").value(DEFAULT_SOURCE_ACCOUNT_BANK_CODE))
            .andExpect(jsonPath("$.sourceAccountName").value(DEFAULT_SOURCE_ACCOUNT_NAME))
            .andExpect(jsonPath("$.sourceNarration").value(DEFAULT_SOURCE_NARRATION))
            .andExpect(jsonPath("$.destinationAccount").value(DEFAULT_DESTINATION_ACCOUNT))
            .andExpect(jsonPath("$.destinationAccountBankCode").value(DEFAULT_DESTINATION_ACCOUNT_BANK_CODE))
            .andExpect(jsonPath("$.destinationAccountName").value(DEFAULT_DESTINATION_ACCOUNT_NAME))
            .andExpect(jsonPath("$.destinationNarration").value(DEFAULT_DESTINATION_NARRATION))
            .andExpect(jsonPath("$.statusWebHook").value(DEFAULT_STATUS_WEB_HOOK))
            .andExpect(jsonPath("$.transactionRef").value(DEFAULT_TRANSACTION_REF))
            .andExpect(jsonPath("$.responseCode").value(DEFAULT_RESPONSE_CODE))
            .andExpect(jsonPath("$.responseMessage").value(DEFAULT_RESPONSE_MESSAGE))
            .andExpect(jsonPath("$.transactionType").value(DEFAULT_TRANSACTION_TYPE.toString()))
            .andExpect(jsonPath("$.schemeOwnerId").value(DEFAULT_SCHEME_OWNER_ID));
    }


    @Test
    @Transactional
    public void getTransactionRequestsByIdFiltering() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        Long id = transactionRequest.getId();

        defaultTransactionRequestShouldBeFound("id.equals=" + id);
        defaultTransactionRequestShouldNotBeFound("id.notEquals=" + id);

        defaultTransactionRequestShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultTransactionRequestShouldNotBeFound("id.greaterThan=" + id);

        defaultTransactionRequestShouldBeFound("id.lessThanOrEqual=" + id);
        defaultTransactionRequestShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllTransactionRequestsByAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where amount equals to DEFAULT_AMOUNT
        defaultTransactionRequestShouldBeFound("amount.equals=" + DEFAULT_AMOUNT);

        // Get all the transactionRequestList where amount equals to UPDATED_AMOUNT
        defaultTransactionRequestShouldNotBeFound("amount.equals=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByAmountIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where amount not equals to DEFAULT_AMOUNT
        defaultTransactionRequestShouldNotBeFound("amount.notEquals=" + DEFAULT_AMOUNT);

        // Get all the transactionRequestList where amount not equals to UPDATED_AMOUNT
        defaultTransactionRequestShouldBeFound("amount.notEquals=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByAmountIsInShouldWork() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where amount in DEFAULT_AMOUNT or UPDATED_AMOUNT
        defaultTransactionRequestShouldBeFound("amount.in=" + DEFAULT_AMOUNT + "," + UPDATED_AMOUNT);

        // Get all the transactionRequestList where amount equals to UPDATED_AMOUNT
        defaultTransactionRequestShouldNotBeFound("amount.in=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where amount is not null
        defaultTransactionRequestShouldBeFound("amount.specified=true");

        // Get all the transactionRequestList where amount is null
        defaultTransactionRequestShouldNotBeFound("amount.specified=false");
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where amount is greater than or equal to DEFAULT_AMOUNT
        defaultTransactionRequestShouldBeFound("amount.greaterThanOrEqual=" + DEFAULT_AMOUNT);

        // Get all the transactionRequestList where amount is greater than or equal to UPDATED_AMOUNT
        defaultTransactionRequestShouldNotBeFound("amount.greaterThanOrEqual=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByAmountIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where amount is less than or equal to DEFAULT_AMOUNT
        defaultTransactionRequestShouldBeFound("amount.lessThanOrEqual=" + DEFAULT_AMOUNT);

        // Get all the transactionRequestList where amount is less than or equal to SMALLER_AMOUNT
        defaultTransactionRequestShouldNotBeFound("amount.lessThanOrEqual=" + SMALLER_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where amount is less than DEFAULT_AMOUNT
        defaultTransactionRequestShouldNotBeFound("amount.lessThan=" + DEFAULT_AMOUNT);

        // Get all the transactionRequestList where amount is less than UPDATED_AMOUNT
        defaultTransactionRequestShouldBeFound("amount.lessThan=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByAmountIsGreaterThanSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where amount is greater than DEFAULT_AMOUNT
        defaultTransactionRequestShouldNotBeFound("amount.greaterThan=" + DEFAULT_AMOUNT);

        // Get all the transactionRequestList where amount is greater than SMALLER_AMOUNT
        defaultTransactionRequestShouldBeFound("amount.greaterThan=" + SMALLER_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllTransactionRequestsByChannelIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where channel equals to DEFAULT_CHANNEL
        defaultTransactionRequestShouldBeFound("channel.equals=" + DEFAULT_CHANNEL);

        // Get all the transactionRequestList where channel equals to UPDATED_CHANNEL
        defaultTransactionRequestShouldNotBeFound("channel.equals=" + UPDATED_CHANNEL);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByChannelIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where channel not equals to DEFAULT_CHANNEL
        defaultTransactionRequestShouldNotBeFound("channel.notEquals=" + DEFAULT_CHANNEL);

        // Get all the transactionRequestList where channel not equals to UPDATED_CHANNEL
        defaultTransactionRequestShouldBeFound("channel.notEquals=" + UPDATED_CHANNEL);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByChannelIsInShouldWork() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where channel in DEFAULT_CHANNEL or UPDATED_CHANNEL
        defaultTransactionRequestShouldBeFound("channel.in=" + DEFAULT_CHANNEL + "," + UPDATED_CHANNEL);

        // Get all the transactionRequestList where channel equals to UPDATED_CHANNEL
        defaultTransactionRequestShouldNotBeFound("channel.in=" + UPDATED_CHANNEL);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByChannelIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where channel is not null
        defaultTransactionRequestShouldBeFound("channel.specified=true");

        // Get all the transactionRequestList where channel is null
        defaultTransactionRequestShouldNotBeFound("channel.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionRequestsByChannelContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where channel contains DEFAULT_CHANNEL
        defaultTransactionRequestShouldBeFound("channel.contains=" + DEFAULT_CHANNEL);

        // Get all the transactionRequestList where channel contains UPDATED_CHANNEL
        defaultTransactionRequestShouldNotBeFound("channel.contains=" + UPDATED_CHANNEL);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByChannelNotContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where channel does not contain DEFAULT_CHANNEL
        defaultTransactionRequestShouldNotBeFound("channel.doesNotContain=" + DEFAULT_CHANNEL);

        // Get all the transactionRequestList where channel does not contain UPDATED_CHANNEL
        defaultTransactionRequestShouldBeFound("channel.doesNotContain=" + UPDATED_CHANNEL);
    }


    @Test
    @Transactional
    public void getAllTransactionRequestsByCurrencyIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where currency equals to DEFAULT_CURRENCY
        defaultTransactionRequestShouldBeFound("currency.equals=" + DEFAULT_CURRENCY);

        // Get all the transactionRequestList where currency equals to UPDATED_CURRENCY
        defaultTransactionRequestShouldNotBeFound("currency.equals=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByCurrencyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where currency not equals to DEFAULT_CURRENCY
        defaultTransactionRequestShouldNotBeFound("currency.notEquals=" + DEFAULT_CURRENCY);

        // Get all the transactionRequestList where currency not equals to UPDATED_CURRENCY
        defaultTransactionRequestShouldBeFound("currency.notEquals=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByCurrencyIsInShouldWork() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where currency in DEFAULT_CURRENCY or UPDATED_CURRENCY
        defaultTransactionRequestShouldBeFound("currency.in=" + DEFAULT_CURRENCY + "," + UPDATED_CURRENCY);

        // Get all the transactionRequestList where currency equals to UPDATED_CURRENCY
        defaultTransactionRequestShouldNotBeFound("currency.in=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByCurrencyIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where currency is not null
        defaultTransactionRequestShouldBeFound("currency.specified=true");

        // Get all the transactionRequestList where currency is null
        defaultTransactionRequestShouldNotBeFound("currency.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionRequestsByCurrencyContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where currency contains DEFAULT_CURRENCY
        defaultTransactionRequestShouldBeFound("currency.contains=" + DEFAULT_CURRENCY);

        // Get all the transactionRequestList where currency contains UPDATED_CURRENCY
        defaultTransactionRequestShouldNotBeFound("currency.contains=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByCurrencyNotContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where currency does not contain DEFAULT_CURRENCY
        defaultTransactionRequestShouldNotBeFound("currency.doesNotContain=" + DEFAULT_CURRENCY);

        // Get all the transactionRequestList where currency does not contain UPDATED_CURRENCY
        defaultTransactionRequestShouldBeFound("currency.doesNotContain=" + UPDATED_CURRENCY);
    }


    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccount equals to DEFAULT_SOURCE_ACCOUNT
        defaultTransactionRequestShouldBeFound("sourceAccount.equals=" + DEFAULT_SOURCE_ACCOUNT);

        // Get all the transactionRequestList where sourceAccount equals to UPDATED_SOURCE_ACCOUNT
        defaultTransactionRequestShouldNotBeFound("sourceAccount.equals=" + UPDATED_SOURCE_ACCOUNT);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccount not equals to DEFAULT_SOURCE_ACCOUNT
        defaultTransactionRequestShouldNotBeFound("sourceAccount.notEquals=" + DEFAULT_SOURCE_ACCOUNT);

        // Get all the transactionRequestList where sourceAccount not equals to UPDATED_SOURCE_ACCOUNT
        defaultTransactionRequestShouldBeFound("sourceAccount.notEquals=" + UPDATED_SOURCE_ACCOUNT);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountIsInShouldWork() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccount in DEFAULT_SOURCE_ACCOUNT or UPDATED_SOURCE_ACCOUNT
        defaultTransactionRequestShouldBeFound("sourceAccount.in=" + DEFAULT_SOURCE_ACCOUNT + "," + UPDATED_SOURCE_ACCOUNT);

        // Get all the transactionRequestList where sourceAccount equals to UPDATED_SOURCE_ACCOUNT
        defaultTransactionRequestShouldNotBeFound("sourceAccount.in=" + UPDATED_SOURCE_ACCOUNT);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccount is not null
        defaultTransactionRequestShouldBeFound("sourceAccount.specified=true");

        // Get all the transactionRequestList where sourceAccount is null
        defaultTransactionRequestShouldNotBeFound("sourceAccount.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccount contains DEFAULT_SOURCE_ACCOUNT
        defaultTransactionRequestShouldBeFound("sourceAccount.contains=" + DEFAULT_SOURCE_ACCOUNT);

        // Get all the transactionRequestList where sourceAccount contains UPDATED_SOURCE_ACCOUNT
        defaultTransactionRequestShouldNotBeFound("sourceAccount.contains=" + UPDATED_SOURCE_ACCOUNT);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountNotContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccount does not contain DEFAULT_SOURCE_ACCOUNT
        defaultTransactionRequestShouldNotBeFound("sourceAccount.doesNotContain=" + DEFAULT_SOURCE_ACCOUNT);

        // Get all the transactionRequestList where sourceAccount does not contain UPDATED_SOURCE_ACCOUNT
        defaultTransactionRequestShouldBeFound("sourceAccount.doesNotContain=" + UPDATED_SOURCE_ACCOUNT);
    }


    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountBankCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccountBankCode equals to DEFAULT_SOURCE_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldBeFound("sourceAccountBankCode.equals=" + DEFAULT_SOURCE_ACCOUNT_BANK_CODE);

        // Get all the transactionRequestList where sourceAccountBankCode equals to UPDATED_SOURCE_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldNotBeFound("sourceAccountBankCode.equals=" + UPDATED_SOURCE_ACCOUNT_BANK_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountBankCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccountBankCode not equals to DEFAULT_SOURCE_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldNotBeFound("sourceAccountBankCode.notEquals=" + DEFAULT_SOURCE_ACCOUNT_BANK_CODE);

        // Get all the transactionRequestList where sourceAccountBankCode not equals to UPDATED_SOURCE_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldBeFound("sourceAccountBankCode.notEquals=" + UPDATED_SOURCE_ACCOUNT_BANK_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountBankCodeIsInShouldWork() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccountBankCode in DEFAULT_SOURCE_ACCOUNT_BANK_CODE or UPDATED_SOURCE_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldBeFound("sourceAccountBankCode.in=" + DEFAULT_SOURCE_ACCOUNT_BANK_CODE + "," + UPDATED_SOURCE_ACCOUNT_BANK_CODE);

        // Get all the transactionRequestList where sourceAccountBankCode equals to UPDATED_SOURCE_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldNotBeFound("sourceAccountBankCode.in=" + UPDATED_SOURCE_ACCOUNT_BANK_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountBankCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccountBankCode is not null
        defaultTransactionRequestShouldBeFound("sourceAccountBankCode.specified=true");

        // Get all the transactionRequestList where sourceAccountBankCode is null
        defaultTransactionRequestShouldNotBeFound("sourceAccountBankCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountBankCodeContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccountBankCode contains DEFAULT_SOURCE_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldBeFound("sourceAccountBankCode.contains=" + DEFAULT_SOURCE_ACCOUNT_BANK_CODE);

        // Get all the transactionRequestList where sourceAccountBankCode contains UPDATED_SOURCE_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldNotBeFound("sourceAccountBankCode.contains=" + UPDATED_SOURCE_ACCOUNT_BANK_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountBankCodeNotContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccountBankCode does not contain DEFAULT_SOURCE_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldNotBeFound("sourceAccountBankCode.doesNotContain=" + DEFAULT_SOURCE_ACCOUNT_BANK_CODE);

        // Get all the transactionRequestList where sourceAccountBankCode does not contain UPDATED_SOURCE_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldBeFound("sourceAccountBankCode.doesNotContain=" + UPDATED_SOURCE_ACCOUNT_BANK_CODE);
    }


    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountNameIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccountName equals to DEFAULT_SOURCE_ACCOUNT_NAME
        defaultTransactionRequestShouldBeFound("sourceAccountName.equals=" + DEFAULT_SOURCE_ACCOUNT_NAME);

        // Get all the transactionRequestList where sourceAccountName equals to UPDATED_SOURCE_ACCOUNT_NAME
        defaultTransactionRequestShouldNotBeFound("sourceAccountName.equals=" + UPDATED_SOURCE_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccountName not equals to DEFAULT_SOURCE_ACCOUNT_NAME
        defaultTransactionRequestShouldNotBeFound("sourceAccountName.notEquals=" + DEFAULT_SOURCE_ACCOUNT_NAME);

        // Get all the transactionRequestList where sourceAccountName not equals to UPDATED_SOURCE_ACCOUNT_NAME
        defaultTransactionRequestShouldBeFound("sourceAccountName.notEquals=" + UPDATED_SOURCE_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountNameIsInShouldWork() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccountName in DEFAULT_SOURCE_ACCOUNT_NAME or UPDATED_SOURCE_ACCOUNT_NAME
        defaultTransactionRequestShouldBeFound("sourceAccountName.in=" + DEFAULT_SOURCE_ACCOUNT_NAME + "," + UPDATED_SOURCE_ACCOUNT_NAME);

        // Get all the transactionRequestList where sourceAccountName equals to UPDATED_SOURCE_ACCOUNT_NAME
        defaultTransactionRequestShouldNotBeFound("sourceAccountName.in=" + UPDATED_SOURCE_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccountName is not null
        defaultTransactionRequestShouldBeFound("sourceAccountName.specified=true");

        // Get all the transactionRequestList where sourceAccountName is null
        defaultTransactionRequestShouldNotBeFound("sourceAccountName.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountNameContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccountName contains DEFAULT_SOURCE_ACCOUNT_NAME
        defaultTransactionRequestShouldBeFound("sourceAccountName.contains=" + DEFAULT_SOURCE_ACCOUNT_NAME);

        // Get all the transactionRequestList where sourceAccountName contains UPDATED_SOURCE_ACCOUNT_NAME
        defaultTransactionRequestShouldNotBeFound("sourceAccountName.contains=" + UPDATED_SOURCE_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceAccountNameNotContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceAccountName does not contain DEFAULT_SOURCE_ACCOUNT_NAME
        defaultTransactionRequestShouldNotBeFound("sourceAccountName.doesNotContain=" + DEFAULT_SOURCE_ACCOUNT_NAME);

        // Get all the transactionRequestList where sourceAccountName does not contain UPDATED_SOURCE_ACCOUNT_NAME
        defaultTransactionRequestShouldBeFound("sourceAccountName.doesNotContain=" + UPDATED_SOURCE_ACCOUNT_NAME);
    }


    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceNarrationIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceNarration equals to DEFAULT_SOURCE_NARRATION
        defaultTransactionRequestShouldBeFound("sourceNarration.equals=" + DEFAULT_SOURCE_NARRATION);

        // Get all the transactionRequestList where sourceNarration equals to UPDATED_SOURCE_NARRATION
        defaultTransactionRequestShouldNotBeFound("sourceNarration.equals=" + UPDATED_SOURCE_NARRATION);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceNarrationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceNarration not equals to DEFAULT_SOURCE_NARRATION
        defaultTransactionRequestShouldNotBeFound("sourceNarration.notEquals=" + DEFAULT_SOURCE_NARRATION);

        // Get all the transactionRequestList where sourceNarration not equals to UPDATED_SOURCE_NARRATION
        defaultTransactionRequestShouldBeFound("sourceNarration.notEquals=" + UPDATED_SOURCE_NARRATION);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceNarrationIsInShouldWork() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceNarration in DEFAULT_SOURCE_NARRATION or UPDATED_SOURCE_NARRATION
        defaultTransactionRequestShouldBeFound("sourceNarration.in=" + DEFAULT_SOURCE_NARRATION + "," + UPDATED_SOURCE_NARRATION);

        // Get all the transactionRequestList where sourceNarration equals to UPDATED_SOURCE_NARRATION
        defaultTransactionRequestShouldNotBeFound("sourceNarration.in=" + UPDATED_SOURCE_NARRATION);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceNarrationIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceNarration is not null
        defaultTransactionRequestShouldBeFound("sourceNarration.specified=true");

        // Get all the transactionRequestList where sourceNarration is null
        defaultTransactionRequestShouldNotBeFound("sourceNarration.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionRequestsBySourceNarrationContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceNarration contains DEFAULT_SOURCE_NARRATION
        defaultTransactionRequestShouldBeFound("sourceNarration.contains=" + DEFAULT_SOURCE_NARRATION);

        // Get all the transactionRequestList where sourceNarration contains UPDATED_SOURCE_NARRATION
        defaultTransactionRequestShouldNotBeFound("sourceNarration.contains=" + UPDATED_SOURCE_NARRATION);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySourceNarrationNotContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where sourceNarration does not contain DEFAULT_SOURCE_NARRATION
        defaultTransactionRequestShouldNotBeFound("sourceNarration.doesNotContain=" + DEFAULT_SOURCE_NARRATION);

        // Get all the transactionRequestList where sourceNarration does not contain UPDATED_SOURCE_NARRATION
        defaultTransactionRequestShouldBeFound("sourceNarration.doesNotContain=" + UPDATED_SOURCE_NARRATION);
    }


    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccount equals to DEFAULT_DESTINATION_ACCOUNT
        defaultTransactionRequestShouldBeFound("destinationAccount.equals=" + DEFAULT_DESTINATION_ACCOUNT);

        // Get all the transactionRequestList where destinationAccount equals to UPDATED_DESTINATION_ACCOUNT
        defaultTransactionRequestShouldNotBeFound("destinationAccount.equals=" + UPDATED_DESTINATION_ACCOUNT);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccount not equals to DEFAULT_DESTINATION_ACCOUNT
        defaultTransactionRequestShouldNotBeFound("destinationAccount.notEquals=" + DEFAULT_DESTINATION_ACCOUNT);

        // Get all the transactionRequestList where destinationAccount not equals to UPDATED_DESTINATION_ACCOUNT
        defaultTransactionRequestShouldBeFound("destinationAccount.notEquals=" + UPDATED_DESTINATION_ACCOUNT);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountIsInShouldWork() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccount in DEFAULT_DESTINATION_ACCOUNT or UPDATED_DESTINATION_ACCOUNT
        defaultTransactionRequestShouldBeFound("destinationAccount.in=" + DEFAULT_DESTINATION_ACCOUNT + "," + UPDATED_DESTINATION_ACCOUNT);

        // Get all the transactionRequestList where destinationAccount equals to UPDATED_DESTINATION_ACCOUNT
        defaultTransactionRequestShouldNotBeFound("destinationAccount.in=" + UPDATED_DESTINATION_ACCOUNT);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccount is not null
        defaultTransactionRequestShouldBeFound("destinationAccount.specified=true");

        // Get all the transactionRequestList where destinationAccount is null
        defaultTransactionRequestShouldNotBeFound("destinationAccount.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccount contains DEFAULT_DESTINATION_ACCOUNT
        defaultTransactionRequestShouldBeFound("destinationAccount.contains=" + DEFAULT_DESTINATION_ACCOUNT);

        // Get all the transactionRequestList where destinationAccount contains UPDATED_DESTINATION_ACCOUNT
        defaultTransactionRequestShouldNotBeFound("destinationAccount.contains=" + UPDATED_DESTINATION_ACCOUNT);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountNotContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccount does not contain DEFAULT_DESTINATION_ACCOUNT
        defaultTransactionRequestShouldNotBeFound("destinationAccount.doesNotContain=" + DEFAULT_DESTINATION_ACCOUNT);

        // Get all the transactionRequestList where destinationAccount does not contain UPDATED_DESTINATION_ACCOUNT
        defaultTransactionRequestShouldBeFound("destinationAccount.doesNotContain=" + UPDATED_DESTINATION_ACCOUNT);
    }


    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountBankCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccountBankCode equals to DEFAULT_DESTINATION_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldBeFound("destinationAccountBankCode.equals=" + DEFAULT_DESTINATION_ACCOUNT_BANK_CODE);

        // Get all the transactionRequestList where destinationAccountBankCode equals to UPDATED_DESTINATION_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldNotBeFound("destinationAccountBankCode.equals=" + UPDATED_DESTINATION_ACCOUNT_BANK_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountBankCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccountBankCode not equals to DEFAULT_DESTINATION_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldNotBeFound("destinationAccountBankCode.notEquals=" + DEFAULT_DESTINATION_ACCOUNT_BANK_CODE);

        // Get all the transactionRequestList where destinationAccountBankCode not equals to UPDATED_DESTINATION_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldBeFound("destinationAccountBankCode.notEquals=" + UPDATED_DESTINATION_ACCOUNT_BANK_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountBankCodeIsInShouldWork() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccountBankCode in DEFAULT_DESTINATION_ACCOUNT_BANK_CODE or UPDATED_DESTINATION_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldBeFound("destinationAccountBankCode.in=" + DEFAULT_DESTINATION_ACCOUNT_BANK_CODE + "," + UPDATED_DESTINATION_ACCOUNT_BANK_CODE);

        // Get all the transactionRequestList where destinationAccountBankCode equals to UPDATED_DESTINATION_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldNotBeFound("destinationAccountBankCode.in=" + UPDATED_DESTINATION_ACCOUNT_BANK_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountBankCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccountBankCode is not null
        defaultTransactionRequestShouldBeFound("destinationAccountBankCode.specified=true");

        // Get all the transactionRequestList where destinationAccountBankCode is null
        defaultTransactionRequestShouldNotBeFound("destinationAccountBankCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountBankCodeContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccountBankCode contains DEFAULT_DESTINATION_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldBeFound("destinationAccountBankCode.contains=" + DEFAULT_DESTINATION_ACCOUNT_BANK_CODE);

        // Get all the transactionRequestList where destinationAccountBankCode contains UPDATED_DESTINATION_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldNotBeFound("destinationAccountBankCode.contains=" + UPDATED_DESTINATION_ACCOUNT_BANK_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountBankCodeNotContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccountBankCode does not contain DEFAULT_DESTINATION_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldNotBeFound("destinationAccountBankCode.doesNotContain=" + DEFAULT_DESTINATION_ACCOUNT_BANK_CODE);

        // Get all the transactionRequestList where destinationAccountBankCode does not contain UPDATED_DESTINATION_ACCOUNT_BANK_CODE
        defaultTransactionRequestShouldBeFound("destinationAccountBankCode.doesNotContain=" + UPDATED_DESTINATION_ACCOUNT_BANK_CODE);
    }


    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountNameIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccountName equals to DEFAULT_DESTINATION_ACCOUNT_NAME
        defaultTransactionRequestShouldBeFound("destinationAccountName.equals=" + DEFAULT_DESTINATION_ACCOUNT_NAME);

        // Get all the transactionRequestList where destinationAccountName equals to UPDATED_DESTINATION_ACCOUNT_NAME
        defaultTransactionRequestShouldNotBeFound("destinationAccountName.equals=" + UPDATED_DESTINATION_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccountName not equals to DEFAULT_DESTINATION_ACCOUNT_NAME
        defaultTransactionRequestShouldNotBeFound("destinationAccountName.notEquals=" + DEFAULT_DESTINATION_ACCOUNT_NAME);

        // Get all the transactionRequestList where destinationAccountName not equals to UPDATED_DESTINATION_ACCOUNT_NAME
        defaultTransactionRequestShouldBeFound("destinationAccountName.notEquals=" + UPDATED_DESTINATION_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountNameIsInShouldWork() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccountName in DEFAULT_DESTINATION_ACCOUNT_NAME or UPDATED_DESTINATION_ACCOUNT_NAME
        defaultTransactionRequestShouldBeFound("destinationAccountName.in=" + DEFAULT_DESTINATION_ACCOUNT_NAME + "," + UPDATED_DESTINATION_ACCOUNT_NAME);

        // Get all the transactionRequestList where destinationAccountName equals to UPDATED_DESTINATION_ACCOUNT_NAME
        defaultTransactionRequestShouldNotBeFound("destinationAccountName.in=" + UPDATED_DESTINATION_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccountName is not null
        defaultTransactionRequestShouldBeFound("destinationAccountName.specified=true");

        // Get all the transactionRequestList where destinationAccountName is null
        defaultTransactionRequestShouldNotBeFound("destinationAccountName.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountNameContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccountName contains DEFAULT_DESTINATION_ACCOUNT_NAME
        defaultTransactionRequestShouldBeFound("destinationAccountName.contains=" + DEFAULT_DESTINATION_ACCOUNT_NAME);

        // Get all the transactionRequestList where destinationAccountName contains UPDATED_DESTINATION_ACCOUNT_NAME
        defaultTransactionRequestShouldNotBeFound("destinationAccountName.contains=" + UPDATED_DESTINATION_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationAccountNameNotContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationAccountName does not contain DEFAULT_DESTINATION_ACCOUNT_NAME
        defaultTransactionRequestShouldNotBeFound("destinationAccountName.doesNotContain=" + DEFAULT_DESTINATION_ACCOUNT_NAME);

        // Get all the transactionRequestList where destinationAccountName does not contain UPDATED_DESTINATION_ACCOUNT_NAME
        defaultTransactionRequestShouldBeFound("destinationAccountName.doesNotContain=" + UPDATED_DESTINATION_ACCOUNT_NAME);
    }


    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationNarrationIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationNarration equals to DEFAULT_DESTINATION_NARRATION
        defaultTransactionRequestShouldBeFound("destinationNarration.equals=" + DEFAULT_DESTINATION_NARRATION);

        // Get all the transactionRequestList where destinationNarration equals to UPDATED_DESTINATION_NARRATION
        defaultTransactionRequestShouldNotBeFound("destinationNarration.equals=" + UPDATED_DESTINATION_NARRATION);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationNarrationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationNarration not equals to DEFAULT_DESTINATION_NARRATION
        defaultTransactionRequestShouldNotBeFound("destinationNarration.notEquals=" + DEFAULT_DESTINATION_NARRATION);

        // Get all the transactionRequestList where destinationNarration not equals to UPDATED_DESTINATION_NARRATION
        defaultTransactionRequestShouldBeFound("destinationNarration.notEquals=" + UPDATED_DESTINATION_NARRATION);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationNarrationIsInShouldWork() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationNarration in DEFAULT_DESTINATION_NARRATION or UPDATED_DESTINATION_NARRATION
        defaultTransactionRequestShouldBeFound("destinationNarration.in=" + DEFAULT_DESTINATION_NARRATION + "," + UPDATED_DESTINATION_NARRATION);

        // Get all the transactionRequestList where destinationNarration equals to UPDATED_DESTINATION_NARRATION
        defaultTransactionRequestShouldNotBeFound("destinationNarration.in=" + UPDATED_DESTINATION_NARRATION);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationNarrationIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationNarration is not null
        defaultTransactionRequestShouldBeFound("destinationNarration.specified=true");

        // Get all the transactionRequestList where destinationNarration is null
        defaultTransactionRequestShouldNotBeFound("destinationNarration.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationNarrationContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationNarration contains DEFAULT_DESTINATION_NARRATION
        defaultTransactionRequestShouldBeFound("destinationNarration.contains=" + DEFAULT_DESTINATION_NARRATION);

        // Get all the transactionRequestList where destinationNarration contains UPDATED_DESTINATION_NARRATION
        defaultTransactionRequestShouldNotBeFound("destinationNarration.contains=" + UPDATED_DESTINATION_NARRATION);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByDestinationNarrationNotContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where destinationNarration does not contain DEFAULT_DESTINATION_NARRATION
        defaultTransactionRequestShouldNotBeFound("destinationNarration.doesNotContain=" + DEFAULT_DESTINATION_NARRATION);

        // Get all the transactionRequestList where destinationNarration does not contain UPDATED_DESTINATION_NARRATION
        defaultTransactionRequestShouldBeFound("destinationNarration.doesNotContain=" + UPDATED_DESTINATION_NARRATION);
    }


    @Test
    @Transactional
    public void getAllTransactionRequestsByStatusWebHookIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where statusWebHook equals to DEFAULT_STATUS_WEB_HOOK
        defaultTransactionRequestShouldBeFound("statusWebHook.equals=" + DEFAULT_STATUS_WEB_HOOK);

        // Get all the transactionRequestList where statusWebHook equals to UPDATED_STATUS_WEB_HOOK
        defaultTransactionRequestShouldNotBeFound("statusWebHook.equals=" + UPDATED_STATUS_WEB_HOOK);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByStatusWebHookIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where statusWebHook not equals to DEFAULT_STATUS_WEB_HOOK
        defaultTransactionRequestShouldNotBeFound("statusWebHook.notEquals=" + DEFAULT_STATUS_WEB_HOOK);

        // Get all the transactionRequestList where statusWebHook not equals to UPDATED_STATUS_WEB_HOOK
        defaultTransactionRequestShouldBeFound("statusWebHook.notEquals=" + UPDATED_STATUS_WEB_HOOK);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByStatusWebHookIsInShouldWork() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where statusWebHook in DEFAULT_STATUS_WEB_HOOK or UPDATED_STATUS_WEB_HOOK
        defaultTransactionRequestShouldBeFound("statusWebHook.in=" + DEFAULT_STATUS_WEB_HOOK + "," + UPDATED_STATUS_WEB_HOOK);

        // Get all the transactionRequestList where statusWebHook equals to UPDATED_STATUS_WEB_HOOK
        defaultTransactionRequestShouldNotBeFound("statusWebHook.in=" + UPDATED_STATUS_WEB_HOOK);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByStatusWebHookIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where statusWebHook is not null
        defaultTransactionRequestShouldBeFound("statusWebHook.specified=true");

        // Get all the transactionRequestList where statusWebHook is null
        defaultTransactionRequestShouldNotBeFound("statusWebHook.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionRequestsByStatusWebHookContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where statusWebHook contains DEFAULT_STATUS_WEB_HOOK
        defaultTransactionRequestShouldBeFound("statusWebHook.contains=" + DEFAULT_STATUS_WEB_HOOK);

        // Get all the transactionRequestList where statusWebHook contains UPDATED_STATUS_WEB_HOOK
        defaultTransactionRequestShouldNotBeFound("statusWebHook.contains=" + UPDATED_STATUS_WEB_HOOK);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByStatusWebHookNotContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where statusWebHook does not contain DEFAULT_STATUS_WEB_HOOK
        defaultTransactionRequestShouldNotBeFound("statusWebHook.doesNotContain=" + DEFAULT_STATUS_WEB_HOOK);

        // Get all the transactionRequestList where statusWebHook does not contain UPDATED_STATUS_WEB_HOOK
        defaultTransactionRequestShouldBeFound("statusWebHook.doesNotContain=" + UPDATED_STATUS_WEB_HOOK);
    }


    @Test
    @Transactional
    public void getAllTransactionRequestsByTransactionRefIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where transactionRef equals to DEFAULT_TRANSACTION_REF
        defaultTransactionRequestShouldBeFound("transactionRef.equals=" + DEFAULT_TRANSACTION_REF);

        // Get all the transactionRequestList where transactionRef equals to UPDATED_TRANSACTION_REF
        defaultTransactionRequestShouldNotBeFound("transactionRef.equals=" + UPDATED_TRANSACTION_REF);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByTransactionRefIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where transactionRef not equals to DEFAULT_TRANSACTION_REF
        defaultTransactionRequestShouldNotBeFound("transactionRef.notEquals=" + DEFAULT_TRANSACTION_REF);

        // Get all the transactionRequestList where transactionRef not equals to UPDATED_TRANSACTION_REF
        defaultTransactionRequestShouldBeFound("transactionRef.notEquals=" + UPDATED_TRANSACTION_REF);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByTransactionRefIsInShouldWork() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where transactionRef in DEFAULT_TRANSACTION_REF or UPDATED_TRANSACTION_REF
        defaultTransactionRequestShouldBeFound("transactionRef.in=" + DEFAULT_TRANSACTION_REF + "," + UPDATED_TRANSACTION_REF);

        // Get all the transactionRequestList where transactionRef equals to UPDATED_TRANSACTION_REF
        defaultTransactionRequestShouldNotBeFound("transactionRef.in=" + UPDATED_TRANSACTION_REF);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByTransactionRefIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where transactionRef is not null
        defaultTransactionRequestShouldBeFound("transactionRef.specified=true");

        // Get all the transactionRequestList where transactionRef is null
        defaultTransactionRequestShouldNotBeFound("transactionRef.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionRequestsByTransactionRefContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where transactionRef contains DEFAULT_TRANSACTION_REF
        defaultTransactionRequestShouldBeFound("transactionRef.contains=" + DEFAULT_TRANSACTION_REF);

        // Get all the transactionRequestList where transactionRef contains UPDATED_TRANSACTION_REF
        defaultTransactionRequestShouldNotBeFound("transactionRef.contains=" + UPDATED_TRANSACTION_REF);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByTransactionRefNotContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where transactionRef does not contain DEFAULT_TRANSACTION_REF
        defaultTransactionRequestShouldNotBeFound("transactionRef.doesNotContain=" + DEFAULT_TRANSACTION_REF);

        // Get all the transactionRequestList where transactionRef does not contain UPDATED_TRANSACTION_REF
        defaultTransactionRequestShouldBeFound("transactionRef.doesNotContain=" + UPDATED_TRANSACTION_REF);
    }


    @Test
    @Transactional
    public void getAllTransactionRequestsByResponseCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where responseCode equals to DEFAULT_RESPONSE_CODE
        defaultTransactionRequestShouldBeFound("responseCode.equals=" + DEFAULT_RESPONSE_CODE);

        // Get all the transactionRequestList where responseCode equals to UPDATED_RESPONSE_CODE
        defaultTransactionRequestShouldNotBeFound("responseCode.equals=" + UPDATED_RESPONSE_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByResponseCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where responseCode not equals to DEFAULT_RESPONSE_CODE
        defaultTransactionRequestShouldNotBeFound("responseCode.notEquals=" + DEFAULT_RESPONSE_CODE);

        // Get all the transactionRequestList where responseCode not equals to UPDATED_RESPONSE_CODE
        defaultTransactionRequestShouldBeFound("responseCode.notEquals=" + UPDATED_RESPONSE_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByResponseCodeIsInShouldWork() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where responseCode in DEFAULT_RESPONSE_CODE or UPDATED_RESPONSE_CODE
        defaultTransactionRequestShouldBeFound("responseCode.in=" + DEFAULT_RESPONSE_CODE + "," + UPDATED_RESPONSE_CODE);

        // Get all the transactionRequestList where responseCode equals to UPDATED_RESPONSE_CODE
        defaultTransactionRequestShouldNotBeFound("responseCode.in=" + UPDATED_RESPONSE_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByResponseCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where responseCode is not null
        defaultTransactionRequestShouldBeFound("responseCode.specified=true");

        // Get all the transactionRequestList where responseCode is null
        defaultTransactionRequestShouldNotBeFound("responseCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionRequestsByResponseCodeContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where responseCode contains DEFAULT_RESPONSE_CODE
        defaultTransactionRequestShouldBeFound("responseCode.contains=" + DEFAULT_RESPONSE_CODE);

        // Get all the transactionRequestList where responseCode contains UPDATED_RESPONSE_CODE
        defaultTransactionRequestShouldNotBeFound("responseCode.contains=" + UPDATED_RESPONSE_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByResponseCodeNotContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where responseCode does not contain DEFAULT_RESPONSE_CODE
        defaultTransactionRequestShouldNotBeFound("responseCode.doesNotContain=" + DEFAULT_RESPONSE_CODE);

        // Get all the transactionRequestList where responseCode does not contain UPDATED_RESPONSE_CODE
        defaultTransactionRequestShouldBeFound("responseCode.doesNotContain=" + UPDATED_RESPONSE_CODE);
    }


    @Test
    @Transactional
    public void getAllTransactionRequestsByResponseMessageIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where responseMessage equals to DEFAULT_RESPONSE_MESSAGE
        defaultTransactionRequestShouldBeFound("responseMessage.equals=" + DEFAULT_RESPONSE_MESSAGE);

        // Get all the transactionRequestList where responseMessage equals to UPDATED_RESPONSE_MESSAGE
        defaultTransactionRequestShouldNotBeFound("responseMessage.equals=" + UPDATED_RESPONSE_MESSAGE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByResponseMessageIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where responseMessage not equals to DEFAULT_RESPONSE_MESSAGE
        defaultTransactionRequestShouldNotBeFound("responseMessage.notEquals=" + DEFAULT_RESPONSE_MESSAGE);

        // Get all the transactionRequestList where responseMessage not equals to UPDATED_RESPONSE_MESSAGE
        defaultTransactionRequestShouldBeFound("responseMessage.notEquals=" + UPDATED_RESPONSE_MESSAGE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByResponseMessageIsInShouldWork() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where responseMessage in DEFAULT_RESPONSE_MESSAGE or UPDATED_RESPONSE_MESSAGE
        defaultTransactionRequestShouldBeFound("responseMessage.in=" + DEFAULT_RESPONSE_MESSAGE + "," + UPDATED_RESPONSE_MESSAGE);

        // Get all the transactionRequestList where responseMessage equals to UPDATED_RESPONSE_MESSAGE
        defaultTransactionRequestShouldNotBeFound("responseMessage.in=" + UPDATED_RESPONSE_MESSAGE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByResponseMessageIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where responseMessage is not null
        defaultTransactionRequestShouldBeFound("responseMessage.specified=true");

        // Get all the transactionRequestList where responseMessage is null
        defaultTransactionRequestShouldNotBeFound("responseMessage.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionRequestsByResponseMessageContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where responseMessage contains DEFAULT_RESPONSE_MESSAGE
        defaultTransactionRequestShouldBeFound("responseMessage.contains=" + DEFAULT_RESPONSE_MESSAGE);

        // Get all the transactionRequestList where responseMessage contains UPDATED_RESPONSE_MESSAGE
        defaultTransactionRequestShouldNotBeFound("responseMessage.contains=" + UPDATED_RESPONSE_MESSAGE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByResponseMessageNotContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where responseMessage does not contain DEFAULT_RESPONSE_MESSAGE
        defaultTransactionRequestShouldNotBeFound("responseMessage.doesNotContain=" + DEFAULT_RESPONSE_MESSAGE);

        // Get all the transactionRequestList where responseMessage does not contain UPDATED_RESPONSE_MESSAGE
        defaultTransactionRequestShouldBeFound("responseMessage.doesNotContain=" + UPDATED_RESPONSE_MESSAGE);
    }


    @Test
    @Transactional
    public void getAllTransactionRequestsByTransactionTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where transactionType equals to DEFAULT_TRANSACTION_TYPE
        defaultTransactionRequestShouldBeFound("transactionType.equals=" + DEFAULT_TRANSACTION_TYPE);

        // Get all the transactionRequestList where transactionType equals to UPDATED_TRANSACTION_TYPE
        defaultTransactionRequestShouldNotBeFound("transactionType.equals=" + UPDATED_TRANSACTION_TYPE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByTransactionTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where transactionType not equals to DEFAULT_TRANSACTION_TYPE
        defaultTransactionRequestShouldNotBeFound("transactionType.notEquals=" + DEFAULT_TRANSACTION_TYPE);

        // Get all the transactionRequestList where transactionType not equals to UPDATED_TRANSACTION_TYPE
        defaultTransactionRequestShouldBeFound("transactionType.notEquals=" + UPDATED_TRANSACTION_TYPE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByTransactionTypeIsInShouldWork() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where transactionType in DEFAULT_TRANSACTION_TYPE or UPDATED_TRANSACTION_TYPE
        defaultTransactionRequestShouldBeFound("transactionType.in=" + DEFAULT_TRANSACTION_TYPE + "," + UPDATED_TRANSACTION_TYPE);

        // Get all the transactionRequestList where transactionType equals to UPDATED_TRANSACTION_TYPE
        defaultTransactionRequestShouldNotBeFound("transactionType.in=" + UPDATED_TRANSACTION_TYPE);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsByTransactionTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where transactionType is not null
        defaultTransactionRequestShouldBeFound("transactionType.specified=true");

        // Get all the transactionRequestList where transactionType is null
        defaultTransactionRequestShouldNotBeFound("transactionType.specified=false");
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySchemeOwnerIdIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where schemeOwnerId equals to DEFAULT_SCHEME_OWNER_ID
        defaultTransactionRequestShouldBeFound("schemeOwnerId.equals=" + DEFAULT_SCHEME_OWNER_ID);

        // Get all the transactionRequestList where schemeOwnerId equals to UPDATED_SCHEME_OWNER_ID
        defaultTransactionRequestShouldNotBeFound("schemeOwnerId.equals=" + UPDATED_SCHEME_OWNER_ID);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySchemeOwnerIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where schemeOwnerId not equals to DEFAULT_SCHEME_OWNER_ID
        defaultTransactionRequestShouldNotBeFound("schemeOwnerId.notEquals=" + DEFAULT_SCHEME_OWNER_ID);

        // Get all the transactionRequestList where schemeOwnerId not equals to UPDATED_SCHEME_OWNER_ID
        defaultTransactionRequestShouldBeFound("schemeOwnerId.notEquals=" + UPDATED_SCHEME_OWNER_ID);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySchemeOwnerIdIsInShouldWork() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where schemeOwnerId in DEFAULT_SCHEME_OWNER_ID or UPDATED_SCHEME_OWNER_ID
        defaultTransactionRequestShouldBeFound("schemeOwnerId.in=" + DEFAULT_SCHEME_OWNER_ID + "," + UPDATED_SCHEME_OWNER_ID);

        // Get all the transactionRequestList where schemeOwnerId equals to UPDATED_SCHEME_OWNER_ID
        defaultTransactionRequestShouldNotBeFound("schemeOwnerId.in=" + UPDATED_SCHEME_OWNER_ID);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySchemeOwnerIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where schemeOwnerId is not null
        defaultTransactionRequestShouldBeFound("schemeOwnerId.specified=true");

        // Get all the transactionRequestList where schemeOwnerId is null
        defaultTransactionRequestShouldNotBeFound("schemeOwnerId.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionRequestsBySchemeOwnerIdContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where schemeOwnerId contains DEFAULT_SCHEME_OWNER_ID
        defaultTransactionRequestShouldBeFound("schemeOwnerId.contains=" + DEFAULT_SCHEME_OWNER_ID);

        // Get all the transactionRequestList where schemeOwnerId contains UPDATED_SCHEME_OWNER_ID
        defaultTransactionRequestShouldNotBeFound("schemeOwnerId.contains=" + UPDATED_SCHEME_OWNER_ID);
    }

    @Test
    @Transactional
    public void getAllTransactionRequestsBySchemeOwnerIdNotContainsSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        // Get all the transactionRequestList where schemeOwnerId does not contain DEFAULT_SCHEME_OWNER_ID
        defaultTransactionRequestShouldNotBeFound("schemeOwnerId.doesNotContain=" + DEFAULT_SCHEME_OWNER_ID);

        // Get all the transactionRequestList where schemeOwnerId does not contain UPDATED_SCHEME_OWNER_ID
        defaultTransactionRequestShouldBeFound("schemeOwnerId.doesNotContain=" + UPDATED_SCHEME_OWNER_ID);
    }


    @Test
    @Transactional
    public void getAllTransactionRequestsByAccountIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);
        VirtualAccount account = VirtualAccountResourceIT.createEntity(em);
        em.persist(account);
        em.flush();
        transactionRequest.addAccount(account);
        transactionRequestRepository.saveAndFlush(transactionRequest);
        Long accountId = account.getId();

        // Get all the transactionRequestList where account equals to accountId
        defaultTransactionRequestShouldBeFound("accountId.equals=" + accountId);

        // Get all the transactionRequestList where account equals to accountId + 1
        defaultTransactionRequestShouldNotBeFound("accountId.equals=" + (accountId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTransactionRequestShouldBeFound(String filter) throws Exception {
        restTransactionRequestMockMvc.perform(get("/api/transaction-requests?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transactionRequest.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].channel").value(hasItem(DEFAULT_CHANNEL)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].sourceAccount").value(hasItem(DEFAULT_SOURCE_ACCOUNT)))
            .andExpect(jsonPath("$.[*].sourceAccountBankCode").value(hasItem(DEFAULT_SOURCE_ACCOUNT_BANK_CODE)))
            .andExpect(jsonPath("$.[*].sourceAccountName").value(hasItem(DEFAULT_SOURCE_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].sourceNarration").value(hasItem(DEFAULT_SOURCE_NARRATION)))
            .andExpect(jsonPath("$.[*].destinationAccount").value(hasItem(DEFAULT_DESTINATION_ACCOUNT)))
            .andExpect(jsonPath("$.[*].destinationAccountBankCode").value(hasItem(DEFAULT_DESTINATION_ACCOUNT_BANK_CODE)))
            .andExpect(jsonPath("$.[*].destinationAccountName").value(hasItem(DEFAULT_DESTINATION_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].destinationNarration").value(hasItem(DEFAULT_DESTINATION_NARRATION)))
            .andExpect(jsonPath("$.[*].statusWebHook").value(hasItem(DEFAULT_STATUS_WEB_HOOK)))
            .andExpect(jsonPath("$.[*].transactionRef").value(hasItem(DEFAULT_TRANSACTION_REF)))
            .andExpect(jsonPath("$.[*].responseCode").value(hasItem(DEFAULT_RESPONSE_CODE)))
            .andExpect(jsonPath("$.[*].responseMessage").value(hasItem(DEFAULT_RESPONSE_MESSAGE)))
            .andExpect(jsonPath("$.[*].transactionType").value(hasItem(DEFAULT_TRANSACTION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].schemeOwnerId").value(hasItem(DEFAULT_SCHEME_OWNER_ID)));

        // Check, that the count call also returns 1
        restTransactionRequestMockMvc.perform(get("/api/transaction-requests/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTransactionRequestShouldNotBeFound(String filter) throws Exception {
        restTransactionRequestMockMvc.perform(get("/api/transaction-requests?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTransactionRequestMockMvc.perform(get("/api/transaction-requests/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingTransactionRequest() throws Exception {
        // Get the transactionRequest
        restTransactionRequestMockMvc.perform(get("/api/transaction-requests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransactionRequest() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        int databaseSizeBeforeUpdate = transactionRequestRepository.findAll().size();

        // Update the transactionRequest
        TransactionRequest updatedTransactionRequest = transactionRequestRepository.findById(transactionRequest.getId()).get();
        // Disconnect from session so that the updates on updatedTransactionRequest are not directly saved in db
        em.detach(updatedTransactionRequest);
        updatedTransactionRequest
            .amount(UPDATED_AMOUNT)
            .channel(UPDATED_CHANNEL)
            .currency(UPDATED_CURRENCY)
            .sourceAccount(UPDATED_SOURCE_ACCOUNT)
            .sourceAccountBankCode(UPDATED_SOURCE_ACCOUNT_BANK_CODE)
            .sourceAccountName(UPDATED_SOURCE_ACCOUNT_NAME)
            .sourceNarration(UPDATED_SOURCE_NARRATION)
            .destinationAccount(UPDATED_DESTINATION_ACCOUNT)
            .destinationAccountBankCode(UPDATED_DESTINATION_ACCOUNT_BANK_CODE)
            .destinationAccountName(UPDATED_DESTINATION_ACCOUNT_NAME)
            .destinationNarration(UPDATED_DESTINATION_NARRATION)
            .statusWebHook(UPDATED_STATUS_WEB_HOOK)
            .transactionRef(UPDATED_TRANSACTION_REF)
            .responseCode(UPDATED_RESPONSE_CODE)
            .responseMessage(UPDATED_RESPONSE_MESSAGE)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .schemeOwnerId(UPDATED_SCHEME_OWNER_ID);
        TransactionRequestDTO transactionRequestDTO = transactionRequestMapper.toDto(updatedTransactionRequest);

        restTransactionRequestMockMvc.perform(put("/api/transaction-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionRequestDTO)))
            .andExpect(status().isOk());

        // Validate the TransactionRequest in the database
        List<TransactionRequest> transactionRequestList = transactionRequestRepository.findAll();
        assertThat(transactionRequestList).hasSize(databaseSizeBeforeUpdate);
        TransactionRequest testTransactionRequest = transactionRequestList.get(transactionRequestList.size() - 1);
        assertThat(testTransactionRequest.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testTransactionRequest.getChannel()).isEqualTo(UPDATED_CHANNEL);
        assertThat(testTransactionRequest.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testTransactionRequest.getSourceAccount()).isEqualTo(UPDATED_SOURCE_ACCOUNT);
        assertThat(testTransactionRequest.getSourceAccountBankCode()).isEqualTo(UPDATED_SOURCE_ACCOUNT_BANK_CODE);
        assertThat(testTransactionRequest.getSourceAccountName()).isEqualTo(UPDATED_SOURCE_ACCOUNT_NAME);
        assertThat(testTransactionRequest.getSourceNarration()).isEqualTo(UPDATED_SOURCE_NARRATION);
        assertThat(testTransactionRequest.getDestinationAccount()).isEqualTo(UPDATED_DESTINATION_ACCOUNT);
        assertThat(testTransactionRequest.getDestinationAccountBankCode()).isEqualTo(UPDATED_DESTINATION_ACCOUNT_BANK_CODE);
        assertThat(testTransactionRequest.getDestinationAccountName()).isEqualTo(UPDATED_DESTINATION_ACCOUNT_NAME);
        assertThat(testTransactionRequest.getDestinationNarration()).isEqualTo(UPDATED_DESTINATION_NARRATION);
        assertThat(testTransactionRequest.getStatusWebHook()).isEqualTo(UPDATED_STATUS_WEB_HOOK);
        assertThat(testTransactionRequest.getTransactionRef()).isEqualTo(UPDATED_TRANSACTION_REF);
        assertThat(testTransactionRequest.getResponseCode()).isEqualTo(UPDATED_RESPONSE_CODE);
        assertThat(testTransactionRequest.getResponseMessage()).isEqualTo(UPDATED_RESPONSE_MESSAGE);
        assertThat(testTransactionRequest.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testTransactionRequest.getSchemeOwnerId()).isEqualTo(UPDATED_SCHEME_OWNER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingTransactionRequest() throws Exception {
        int databaseSizeBeforeUpdate = transactionRequestRepository.findAll().size();

        // Create the TransactionRequest
        TransactionRequestDTO transactionRequestDTO = transactionRequestMapper.toDto(transactionRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionRequestMockMvc.perform(put("/api/transaction-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionRequestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TransactionRequest in the database
        List<TransactionRequest> transactionRequestList = transactionRequestRepository.findAll();
        assertThat(transactionRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransactionRequest() throws Exception {
        // Initialize the database
        transactionRequestRepository.saveAndFlush(transactionRequest);

        int databaseSizeBeforeDelete = transactionRequestRepository.findAll().size();

        // Delete the transactionRequest
        restTransactionRequestMockMvc.perform(delete("/api/transaction-requests/{id}", transactionRequest.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TransactionRequest> transactionRequestList = transactionRequestRepository.findAll();
        assertThat(transactionRequestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
