package com.jhipster.nigeria.web.rest;

import com.jhipster.nigeria.RedisTestContainerExtension;
import com.jhipster.nigeria.CoreserviceApp;
import com.jhipster.nigeria.config.SecurityBeanOverrideConfiguration;
import com.jhipster.nigeria.domain.VirtualAccount;
import com.jhipster.nigeria.domain.Profile;
import com.jhipster.nigeria.domain.TransactionRequest;
import com.jhipster.nigeria.repository.VirtualAccountRepository;
import com.jhipster.nigeria.service.VirtualAccountService;
import com.jhipster.nigeria.service.dto.VirtualAccountDTO;
import com.jhipster.nigeria.service.mapper.VirtualAccountMapper;
import com.jhipster.nigeria.service.dto.VirtualAccountCriteria;
import com.jhipster.nigeria.service.VirtualAccountQueryService;

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

import com.jhipster.nigeria.domain.enumeration.ProfileType;
import com.jhipster.nigeria.domain.enumeration.AccountStatus;
/**
 * Integration tests for the {@link VirtualAccountResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, CoreserviceApp.class })
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class VirtualAccountResourceIT {

    private static final String DEFAULT_CUSTOMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_EXT_CUSTOMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXT_CUSTOMER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AVAILABLE_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_AVAILABLE_BALANCE = new BigDecimal(2);
    private static final BigDecimal SMALLER_AVAILABLE_BALANCE = new BigDecimal(1 - 1);

    private static final BigDecimal DEFAULT_HOLD_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_HOLD_BALANCE = new BigDecimal(2);
    private static final BigDecimal SMALLER_HOLD_BALANCE = new BigDecimal(1 - 1);

    private static final BigDecimal DEFAULT_MINIMUM_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MINIMUM_BALANCE = new BigDecimal(2);
    private static final BigDecimal SMALLER_MINIMUM_BALANCE = new BigDecimal(1 - 1);

    private static final ProfileType DEFAULT_ACCOUNT_TYPE = ProfileType.PERSONAL;
    private static final ProfileType UPDATED_ACCOUNT_TYPE = ProfileType.BUSINESS;

    private static final AccountStatus DEFAULT_STATUS = AccountStatus.NEW;
    private static final AccountStatus UPDATED_STATUS = AccountStatus.ACTIVE;

    @Autowired
    private VirtualAccountRepository virtualAccountRepository;

    @Autowired
    private VirtualAccountMapper virtualAccountMapper;

    @Autowired
    private VirtualAccountService virtualAccountService;

    @Autowired
    private VirtualAccountQueryService virtualAccountQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVirtualAccountMockMvc;

    private VirtualAccount virtualAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VirtualAccount createEntity(EntityManager em) {
        VirtualAccount virtualAccount = new VirtualAccount()
            .customerId(DEFAULT_CUSTOMER_ID)
            .extCustomerId(DEFAULT_EXT_CUSTOMER_ID)
            .currency(DEFAULT_CURRENCY)
            .accountNumber(DEFAULT_ACCOUNT_NUMBER)
            .availableBalance(DEFAULT_AVAILABLE_BALANCE)
            .holdBalance(DEFAULT_HOLD_BALANCE)
            .minimumBalance(DEFAULT_MINIMUM_BALANCE)
            .accountType(DEFAULT_ACCOUNT_TYPE)
            .status(DEFAULT_STATUS);
        return virtualAccount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VirtualAccount createUpdatedEntity(EntityManager em) {
        VirtualAccount virtualAccount = new VirtualAccount()
            .customerId(UPDATED_CUSTOMER_ID)
            .extCustomerId(UPDATED_EXT_CUSTOMER_ID)
            .currency(UPDATED_CURRENCY)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .availableBalance(UPDATED_AVAILABLE_BALANCE)
            .holdBalance(UPDATED_HOLD_BALANCE)
            .minimumBalance(UPDATED_MINIMUM_BALANCE)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .status(UPDATED_STATUS);
        return virtualAccount;
    }

    @BeforeEach
    public void initTest() {
        virtualAccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createVirtualAccount() throws Exception {
        int databaseSizeBeforeCreate = virtualAccountRepository.findAll().size();
        // Create the VirtualAccount
        VirtualAccountDTO virtualAccountDTO = virtualAccountMapper.toDto(virtualAccount);
        restVirtualAccountMockMvc.perform(post("/api/virtual-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(virtualAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the VirtualAccount in the database
        List<VirtualAccount> virtualAccountList = virtualAccountRepository.findAll();
        assertThat(virtualAccountList).hasSize(databaseSizeBeforeCreate + 1);
        VirtualAccount testVirtualAccount = virtualAccountList.get(virtualAccountList.size() - 1);
        assertThat(testVirtualAccount.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testVirtualAccount.getExtCustomerId()).isEqualTo(DEFAULT_EXT_CUSTOMER_ID);
        assertThat(testVirtualAccount.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testVirtualAccount.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testVirtualAccount.getAvailableBalance()).isEqualTo(DEFAULT_AVAILABLE_BALANCE);
        assertThat(testVirtualAccount.getHoldBalance()).isEqualTo(DEFAULT_HOLD_BALANCE);
        assertThat(testVirtualAccount.getMinimumBalance()).isEqualTo(DEFAULT_MINIMUM_BALANCE);
        assertThat(testVirtualAccount.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testVirtualAccount.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createVirtualAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = virtualAccountRepository.findAll().size();

        // Create the VirtualAccount with an existing ID
        virtualAccount.setId(1L);
        VirtualAccountDTO virtualAccountDTO = virtualAccountMapper.toDto(virtualAccount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVirtualAccountMockMvc.perform(post("/api/virtual-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(virtualAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VirtualAccount in the database
        List<VirtualAccount> virtualAccountList = virtualAccountRepository.findAll();
        assertThat(virtualAccountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCustomerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = virtualAccountRepository.findAll().size();
        // set the field null
        virtualAccount.setCustomerId(null);

        // Create the VirtualAccount, which fails.
        VirtualAccountDTO virtualAccountDTO = virtualAccountMapper.toDto(virtualAccount);


        restVirtualAccountMockMvc.perform(post("/api/virtual-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(virtualAccountDTO)))
            .andExpect(status().isBadRequest());

        List<VirtualAccount> virtualAccountList = virtualAccountRepository.findAll();
        assertThat(virtualAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExtCustomerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = virtualAccountRepository.findAll().size();
        // set the field null
        virtualAccount.setExtCustomerId(null);

        // Create the VirtualAccount, which fails.
        VirtualAccountDTO virtualAccountDTO = virtualAccountMapper.toDto(virtualAccount);


        restVirtualAccountMockMvc.perform(post("/api/virtual-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(virtualAccountDTO)))
            .andExpect(status().isBadRequest());

        List<VirtualAccount> virtualAccountList = virtualAccountRepository.findAll();
        assertThat(virtualAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = virtualAccountRepository.findAll().size();
        // set the field null
        virtualAccount.setCurrency(null);

        // Create the VirtualAccount, which fails.
        VirtualAccountDTO virtualAccountDTO = virtualAccountMapper.toDto(virtualAccount);


        restVirtualAccountMockMvc.perform(post("/api/virtual-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(virtualAccountDTO)))
            .andExpect(status().isBadRequest());

        List<VirtualAccount> virtualAccountList = virtualAccountRepository.findAll();
        assertThat(virtualAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = virtualAccountRepository.findAll().size();
        // set the field null
        virtualAccount.setAccountNumber(null);

        // Create the VirtualAccount, which fails.
        VirtualAccountDTO virtualAccountDTO = virtualAccountMapper.toDto(virtualAccount);


        restVirtualAccountMockMvc.perform(post("/api/virtual-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(virtualAccountDTO)))
            .andExpect(status().isBadRequest());

        List<VirtualAccount> virtualAccountList = virtualAccountRepository.findAll();
        assertThat(virtualAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAccountTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = virtualAccountRepository.findAll().size();
        // set the field null
        virtualAccount.setAccountType(null);

        // Create the VirtualAccount, which fails.
        VirtualAccountDTO virtualAccountDTO = virtualAccountMapper.toDto(virtualAccount);


        restVirtualAccountMockMvc.perform(post("/api/virtual-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(virtualAccountDTO)))
            .andExpect(status().isBadRequest());

        List<VirtualAccount> virtualAccountList = virtualAccountRepository.findAll();
        assertThat(virtualAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = virtualAccountRepository.findAll().size();
        // set the field null
        virtualAccount.setStatus(null);

        // Create the VirtualAccount, which fails.
        VirtualAccountDTO virtualAccountDTO = virtualAccountMapper.toDto(virtualAccount);


        restVirtualAccountMockMvc.perform(post("/api/virtual-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(virtualAccountDTO)))
            .andExpect(status().isBadRequest());

        List<VirtualAccount> virtualAccountList = virtualAccountRepository.findAll();
        assertThat(virtualAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVirtualAccounts() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList
        restVirtualAccountMockMvc.perform(get("/api/virtual-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(virtualAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID)))
            .andExpect(jsonPath("$.[*].extCustomerId").value(hasItem(DEFAULT_EXT_CUSTOMER_ID)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].availableBalance").value(hasItem(DEFAULT_AVAILABLE_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].holdBalance").value(hasItem(DEFAULT_HOLD_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].minimumBalance").value(hasItem(DEFAULT_MINIMUM_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].accountType").value(hasItem(DEFAULT_ACCOUNT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getVirtualAccount() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get the virtualAccount
        restVirtualAccountMockMvc.perform(get("/api/virtual-accounts/{id}", virtualAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(virtualAccount.getId().intValue()))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID))
            .andExpect(jsonPath("$.extCustomerId").value(DEFAULT_EXT_CUSTOMER_ID))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.availableBalance").value(DEFAULT_AVAILABLE_BALANCE.intValue()))
            .andExpect(jsonPath("$.holdBalance").value(DEFAULT_HOLD_BALANCE.intValue()))
            .andExpect(jsonPath("$.minimumBalance").value(DEFAULT_MINIMUM_BALANCE.intValue()))
            .andExpect(jsonPath("$.accountType").value(DEFAULT_ACCOUNT_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }


    @Test
    @Transactional
    public void getVirtualAccountsByIdFiltering() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        Long id = virtualAccount.getId();

        defaultVirtualAccountShouldBeFound("id.equals=" + id);
        defaultVirtualAccountShouldNotBeFound("id.notEquals=" + id);

        defaultVirtualAccountShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultVirtualAccountShouldNotBeFound("id.greaterThan=" + id);

        defaultVirtualAccountShouldBeFound("id.lessThanOrEqual=" + id);
        defaultVirtualAccountShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllVirtualAccountsByCustomerIdIsEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where customerId equals to DEFAULT_CUSTOMER_ID
        defaultVirtualAccountShouldBeFound("customerId.equals=" + DEFAULT_CUSTOMER_ID);

        // Get all the virtualAccountList where customerId equals to UPDATED_CUSTOMER_ID
        defaultVirtualAccountShouldNotBeFound("customerId.equals=" + UPDATED_CUSTOMER_ID);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByCustomerIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where customerId not equals to DEFAULT_CUSTOMER_ID
        defaultVirtualAccountShouldNotBeFound("customerId.notEquals=" + DEFAULT_CUSTOMER_ID);

        // Get all the virtualAccountList where customerId not equals to UPDATED_CUSTOMER_ID
        defaultVirtualAccountShouldBeFound("customerId.notEquals=" + UPDATED_CUSTOMER_ID);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByCustomerIdIsInShouldWork() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where customerId in DEFAULT_CUSTOMER_ID or UPDATED_CUSTOMER_ID
        defaultVirtualAccountShouldBeFound("customerId.in=" + DEFAULT_CUSTOMER_ID + "," + UPDATED_CUSTOMER_ID);

        // Get all the virtualAccountList where customerId equals to UPDATED_CUSTOMER_ID
        defaultVirtualAccountShouldNotBeFound("customerId.in=" + UPDATED_CUSTOMER_ID);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByCustomerIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where customerId is not null
        defaultVirtualAccountShouldBeFound("customerId.specified=true");

        // Get all the virtualAccountList where customerId is null
        defaultVirtualAccountShouldNotBeFound("customerId.specified=false");
    }
                @Test
    @Transactional
    public void getAllVirtualAccountsByCustomerIdContainsSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where customerId contains DEFAULT_CUSTOMER_ID
        defaultVirtualAccountShouldBeFound("customerId.contains=" + DEFAULT_CUSTOMER_ID);

        // Get all the virtualAccountList where customerId contains UPDATED_CUSTOMER_ID
        defaultVirtualAccountShouldNotBeFound("customerId.contains=" + UPDATED_CUSTOMER_ID);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByCustomerIdNotContainsSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where customerId does not contain DEFAULT_CUSTOMER_ID
        defaultVirtualAccountShouldNotBeFound("customerId.doesNotContain=" + DEFAULT_CUSTOMER_ID);

        // Get all the virtualAccountList where customerId does not contain UPDATED_CUSTOMER_ID
        defaultVirtualAccountShouldBeFound("customerId.doesNotContain=" + UPDATED_CUSTOMER_ID);
    }


    @Test
    @Transactional
    public void getAllVirtualAccountsByExtCustomerIdIsEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where extCustomerId equals to DEFAULT_EXT_CUSTOMER_ID
        defaultVirtualAccountShouldBeFound("extCustomerId.equals=" + DEFAULT_EXT_CUSTOMER_ID);

        // Get all the virtualAccountList where extCustomerId equals to UPDATED_EXT_CUSTOMER_ID
        defaultVirtualAccountShouldNotBeFound("extCustomerId.equals=" + UPDATED_EXT_CUSTOMER_ID);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByExtCustomerIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where extCustomerId not equals to DEFAULT_EXT_CUSTOMER_ID
        defaultVirtualAccountShouldNotBeFound("extCustomerId.notEquals=" + DEFAULT_EXT_CUSTOMER_ID);

        // Get all the virtualAccountList where extCustomerId not equals to UPDATED_EXT_CUSTOMER_ID
        defaultVirtualAccountShouldBeFound("extCustomerId.notEquals=" + UPDATED_EXT_CUSTOMER_ID);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByExtCustomerIdIsInShouldWork() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where extCustomerId in DEFAULT_EXT_CUSTOMER_ID or UPDATED_EXT_CUSTOMER_ID
        defaultVirtualAccountShouldBeFound("extCustomerId.in=" + DEFAULT_EXT_CUSTOMER_ID + "," + UPDATED_EXT_CUSTOMER_ID);

        // Get all the virtualAccountList where extCustomerId equals to UPDATED_EXT_CUSTOMER_ID
        defaultVirtualAccountShouldNotBeFound("extCustomerId.in=" + UPDATED_EXT_CUSTOMER_ID);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByExtCustomerIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where extCustomerId is not null
        defaultVirtualAccountShouldBeFound("extCustomerId.specified=true");

        // Get all the virtualAccountList where extCustomerId is null
        defaultVirtualAccountShouldNotBeFound("extCustomerId.specified=false");
    }
                @Test
    @Transactional
    public void getAllVirtualAccountsByExtCustomerIdContainsSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where extCustomerId contains DEFAULT_EXT_CUSTOMER_ID
        defaultVirtualAccountShouldBeFound("extCustomerId.contains=" + DEFAULT_EXT_CUSTOMER_ID);

        // Get all the virtualAccountList where extCustomerId contains UPDATED_EXT_CUSTOMER_ID
        defaultVirtualAccountShouldNotBeFound("extCustomerId.contains=" + UPDATED_EXT_CUSTOMER_ID);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByExtCustomerIdNotContainsSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where extCustomerId does not contain DEFAULT_EXT_CUSTOMER_ID
        defaultVirtualAccountShouldNotBeFound("extCustomerId.doesNotContain=" + DEFAULT_EXT_CUSTOMER_ID);

        // Get all the virtualAccountList where extCustomerId does not contain UPDATED_EXT_CUSTOMER_ID
        defaultVirtualAccountShouldBeFound("extCustomerId.doesNotContain=" + UPDATED_EXT_CUSTOMER_ID);
    }


    @Test
    @Transactional
    public void getAllVirtualAccountsByCurrencyIsEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where currency equals to DEFAULT_CURRENCY
        defaultVirtualAccountShouldBeFound("currency.equals=" + DEFAULT_CURRENCY);

        // Get all the virtualAccountList where currency equals to UPDATED_CURRENCY
        defaultVirtualAccountShouldNotBeFound("currency.equals=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByCurrencyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where currency not equals to DEFAULT_CURRENCY
        defaultVirtualAccountShouldNotBeFound("currency.notEquals=" + DEFAULT_CURRENCY);

        // Get all the virtualAccountList where currency not equals to UPDATED_CURRENCY
        defaultVirtualAccountShouldBeFound("currency.notEquals=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByCurrencyIsInShouldWork() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where currency in DEFAULT_CURRENCY or UPDATED_CURRENCY
        defaultVirtualAccountShouldBeFound("currency.in=" + DEFAULT_CURRENCY + "," + UPDATED_CURRENCY);

        // Get all the virtualAccountList where currency equals to UPDATED_CURRENCY
        defaultVirtualAccountShouldNotBeFound("currency.in=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByCurrencyIsNullOrNotNull() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where currency is not null
        defaultVirtualAccountShouldBeFound("currency.specified=true");

        // Get all the virtualAccountList where currency is null
        defaultVirtualAccountShouldNotBeFound("currency.specified=false");
    }
                @Test
    @Transactional
    public void getAllVirtualAccountsByCurrencyContainsSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where currency contains DEFAULT_CURRENCY
        defaultVirtualAccountShouldBeFound("currency.contains=" + DEFAULT_CURRENCY);

        // Get all the virtualAccountList where currency contains UPDATED_CURRENCY
        defaultVirtualAccountShouldNotBeFound("currency.contains=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByCurrencyNotContainsSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where currency does not contain DEFAULT_CURRENCY
        defaultVirtualAccountShouldNotBeFound("currency.doesNotContain=" + DEFAULT_CURRENCY);

        // Get all the virtualAccountList where currency does not contain UPDATED_CURRENCY
        defaultVirtualAccountShouldBeFound("currency.doesNotContain=" + UPDATED_CURRENCY);
    }


    @Test
    @Transactional
    public void getAllVirtualAccountsByAccountNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where accountNumber equals to DEFAULT_ACCOUNT_NUMBER
        defaultVirtualAccountShouldBeFound("accountNumber.equals=" + DEFAULT_ACCOUNT_NUMBER);

        // Get all the virtualAccountList where accountNumber equals to UPDATED_ACCOUNT_NUMBER
        defaultVirtualAccountShouldNotBeFound("accountNumber.equals=" + UPDATED_ACCOUNT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByAccountNumberIsNotEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where accountNumber not equals to DEFAULT_ACCOUNT_NUMBER
        defaultVirtualAccountShouldNotBeFound("accountNumber.notEquals=" + DEFAULT_ACCOUNT_NUMBER);

        // Get all the virtualAccountList where accountNumber not equals to UPDATED_ACCOUNT_NUMBER
        defaultVirtualAccountShouldBeFound("accountNumber.notEquals=" + UPDATED_ACCOUNT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByAccountNumberIsInShouldWork() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where accountNumber in DEFAULT_ACCOUNT_NUMBER or UPDATED_ACCOUNT_NUMBER
        defaultVirtualAccountShouldBeFound("accountNumber.in=" + DEFAULT_ACCOUNT_NUMBER + "," + UPDATED_ACCOUNT_NUMBER);

        // Get all the virtualAccountList where accountNumber equals to UPDATED_ACCOUNT_NUMBER
        defaultVirtualAccountShouldNotBeFound("accountNumber.in=" + UPDATED_ACCOUNT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByAccountNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where accountNumber is not null
        defaultVirtualAccountShouldBeFound("accountNumber.specified=true");

        // Get all the virtualAccountList where accountNumber is null
        defaultVirtualAccountShouldNotBeFound("accountNumber.specified=false");
    }
                @Test
    @Transactional
    public void getAllVirtualAccountsByAccountNumberContainsSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where accountNumber contains DEFAULT_ACCOUNT_NUMBER
        defaultVirtualAccountShouldBeFound("accountNumber.contains=" + DEFAULT_ACCOUNT_NUMBER);

        // Get all the virtualAccountList where accountNumber contains UPDATED_ACCOUNT_NUMBER
        defaultVirtualAccountShouldNotBeFound("accountNumber.contains=" + UPDATED_ACCOUNT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByAccountNumberNotContainsSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where accountNumber does not contain DEFAULT_ACCOUNT_NUMBER
        defaultVirtualAccountShouldNotBeFound("accountNumber.doesNotContain=" + DEFAULT_ACCOUNT_NUMBER);

        // Get all the virtualAccountList where accountNumber does not contain UPDATED_ACCOUNT_NUMBER
        defaultVirtualAccountShouldBeFound("accountNumber.doesNotContain=" + UPDATED_ACCOUNT_NUMBER);
    }


    @Test
    @Transactional
    public void getAllVirtualAccountsByAvailableBalanceIsEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where availableBalance equals to DEFAULT_AVAILABLE_BALANCE
        defaultVirtualAccountShouldBeFound("availableBalance.equals=" + DEFAULT_AVAILABLE_BALANCE);

        // Get all the virtualAccountList where availableBalance equals to UPDATED_AVAILABLE_BALANCE
        defaultVirtualAccountShouldNotBeFound("availableBalance.equals=" + UPDATED_AVAILABLE_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByAvailableBalanceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where availableBalance not equals to DEFAULT_AVAILABLE_BALANCE
        defaultVirtualAccountShouldNotBeFound("availableBalance.notEquals=" + DEFAULT_AVAILABLE_BALANCE);

        // Get all the virtualAccountList where availableBalance not equals to UPDATED_AVAILABLE_BALANCE
        defaultVirtualAccountShouldBeFound("availableBalance.notEquals=" + UPDATED_AVAILABLE_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByAvailableBalanceIsInShouldWork() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where availableBalance in DEFAULT_AVAILABLE_BALANCE or UPDATED_AVAILABLE_BALANCE
        defaultVirtualAccountShouldBeFound("availableBalance.in=" + DEFAULT_AVAILABLE_BALANCE + "," + UPDATED_AVAILABLE_BALANCE);

        // Get all the virtualAccountList where availableBalance equals to UPDATED_AVAILABLE_BALANCE
        defaultVirtualAccountShouldNotBeFound("availableBalance.in=" + UPDATED_AVAILABLE_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByAvailableBalanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where availableBalance is not null
        defaultVirtualAccountShouldBeFound("availableBalance.specified=true");

        // Get all the virtualAccountList where availableBalance is null
        defaultVirtualAccountShouldNotBeFound("availableBalance.specified=false");
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByAvailableBalanceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where availableBalance is greater than or equal to DEFAULT_AVAILABLE_BALANCE
        defaultVirtualAccountShouldBeFound("availableBalance.greaterThanOrEqual=" + DEFAULT_AVAILABLE_BALANCE);

        // Get all the virtualAccountList where availableBalance is greater than or equal to UPDATED_AVAILABLE_BALANCE
        defaultVirtualAccountShouldNotBeFound("availableBalance.greaterThanOrEqual=" + UPDATED_AVAILABLE_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByAvailableBalanceIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where availableBalance is less than or equal to DEFAULT_AVAILABLE_BALANCE
        defaultVirtualAccountShouldBeFound("availableBalance.lessThanOrEqual=" + DEFAULT_AVAILABLE_BALANCE);

        // Get all the virtualAccountList where availableBalance is less than or equal to SMALLER_AVAILABLE_BALANCE
        defaultVirtualAccountShouldNotBeFound("availableBalance.lessThanOrEqual=" + SMALLER_AVAILABLE_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByAvailableBalanceIsLessThanSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where availableBalance is less than DEFAULT_AVAILABLE_BALANCE
        defaultVirtualAccountShouldNotBeFound("availableBalance.lessThan=" + DEFAULT_AVAILABLE_BALANCE);

        // Get all the virtualAccountList where availableBalance is less than UPDATED_AVAILABLE_BALANCE
        defaultVirtualAccountShouldBeFound("availableBalance.lessThan=" + UPDATED_AVAILABLE_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByAvailableBalanceIsGreaterThanSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where availableBalance is greater than DEFAULT_AVAILABLE_BALANCE
        defaultVirtualAccountShouldNotBeFound("availableBalance.greaterThan=" + DEFAULT_AVAILABLE_BALANCE);

        // Get all the virtualAccountList where availableBalance is greater than SMALLER_AVAILABLE_BALANCE
        defaultVirtualAccountShouldBeFound("availableBalance.greaterThan=" + SMALLER_AVAILABLE_BALANCE);
    }


    @Test
    @Transactional
    public void getAllVirtualAccountsByHoldBalanceIsEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where holdBalance equals to DEFAULT_HOLD_BALANCE
        defaultVirtualAccountShouldBeFound("holdBalance.equals=" + DEFAULT_HOLD_BALANCE);

        // Get all the virtualAccountList where holdBalance equals to UPDATED_HOLD_BALANCE
        defaultVirtualAccountShouldNotBeFound("holdBalance.equals=" + UPDATED_HOLD_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByHoldBalanceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where holdBalance not equals to DEFAULT_HOLD_BALANCE
        defaultVirtualAccountShouldNotBeFound("holdBalance.notEquals=" + DEFAULT_HOLD_BALANCE);

        // Get all the virtualAccountList where holdBalance not equals to UPDATED_HOLD_BALANCE
        defaultVirtualAccountShouldBeFound("holdBalance.notEquals=" + UPDATED_HOLD_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByHoldBalanceIsInShouldWork() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where holdBalance in DEFAULT_HOLD_BALANCE or UPDATED_HOLD_BALANCE
        defaultVirtualAccountShouldBeFound("holdBalance.in=" + DEFAULT_HOLD_BALANCE + "," + UPDATED_HOLD_BALANCE);

        // Get all the virtualAccountList where holdBalance equals to UPDATED_HOLD_BALANCE
        defaultVirtualAccountShouldNotBeFound("holdBalance.in=" + UPDATED_HOLD_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByHoldBalanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where holdBalance is not null
        defaultVirtualAccountShouldBeFound("holdBalance.specified=true");

        // Get all the virtualAccountList where holdBalance is null
        defaultVirtualAccountShouldNotBeFound("holdBalance.specified=false");
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByHoldBalanceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where holdBalance is greater than or equal to DEFAULT_HOLD_BALANCE
        defaultVirtualAccountShouldBeFound("holdBalance.greaterThanOrEqual=" + DEFAULT_HOLD_BALANCE);

        // Get all the virtualAccountList where holdBalance is greater than or equal to UPDATED_HOLD_BALANCE
        defaultVirtualAccountShouldNotBeFound("holdBalance.greaterThanOrEqual=" + UPDATED_HOLD_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByHoldBalanceIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where holdBalance is less than or equal to DEFAULT_HOLD_BALANCE
        defaultVirtualAccountShouldBeFound("holdBalance.lessThanOrEqual=" + DEFAULT_HOLD_BALANCE);

        // Get all the virtualAccountList where holdBalance is less than or equal to SMALLER_HOLD_BALANCE
        defaultVirtualAccountShouldNotBeFound("holdBalance.lessThanOrEqual=" + SMALLER_HOLD_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByHoldBalanceIsLessThanSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where holdBalance is less than DEFAULT_HOLD_BALANCE
        defaultVirtualAccountShouldNotBeFound("holdBalance.lessThan=" + DEFAULT_HOLD_BALANCE);

        // Get all the virtualAccountList where holdBalance is less than UPDATED_HOLD_BALANCE
        defaultVirtualAccountShouldBeFound("holdBalance.lessThan=" + UPDATED_HOLD_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByHoldBalanceIsGreaterThanSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where holdBalance is greater than DEFAULT_HOLD_BALANCE
        defaultVirtualAccountShouldNotBeFound("holdBalance.greaterThan=" + DEFAULT_HOLD_BALANCE);

        // Get all the virtualAccountList where holdBalance is greater than SMALLER_HOLD_BALANCE
        defaultVirtualAccountShouldBeFound("holdBalance.greaterThan=" + SMALLER_HOLD_BALANCE);
    }


    @Test
    @Transactional
    public void getAllVirtualAccountsByMinimumBalanceIsEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where minimumBalance equals to DEFAULT_MINIMUM_BALANCE
        defaultVirtualAccountShouldBeFound("minimumBalance.equals=" + DEFAULT_MINIMUM_BALANCE);

        // Get all the virtualAccountList where minimumBalance equals to UPDATED_MINIMUM_BALANCE
        defaultVirtualAccountShouldNotBeFound("minimumBalance.equals=" + UPDATED_MINIMUM_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByMinimumBalanceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where minimumBalance not equals to DEFAULT_MINIMUM_BALANCE
        defaultVirtualAccountShouldNotBeFound("minimumBalance.notEquals=" + DEFAULT_MINIMUM_BALANCE);

        // Get all the virtualAccountList where minimumBalance not equals to UPDATED_MINIMUM_BALANCE
        defaultVirtualAccountShouldBeFound("minimumBalance.notEquals=" + UPDATED_MINIMUM_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByMinimumBalanceIsInShouldWork() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where minimumBalance in DEFAULT_MINIMUM_BALANCE or UPDATED_MINIMUM_BALANCE
        defaultVirtualAccountShouldBeFound("minimumBalance.in=" + DEFAULT_MINIMUM_BALANCE + "," + UPDATED_MINIMUM_BALANCE);

        // Get all the virtualAccountList where minimumBalance equals to UPDATED_MINIMUM_BALANCE
        defaultVirtualAccountShouldNotBeFound("minimumBalance.in=" + UPDATED_MINIMUM_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByMinimumBalanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where minimumBalance is not null
        defaultVirtualAccountShouldBeFound("minimumBalance.specified=true");

        // Get all the virtualAccountList where minimumBalance is null
        defaultVirtualAccountShouldNotBeFound("minimumBalance.specified=false");
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByMinimumBalanceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where minimumBalance is greater than or equal to DEFAULT_MINIMUM_BALANCE
        defaultVirtualAccountShouldBeFound("minimumBalance.greaterThanOrEqual=" + DEFAULT_MINIMUM_BALANCE);

        // Get all the virtualAccountList where minimumBalance is greater than or equal to UPDATED_MINIMUM_BALANCE
        defaultVirtualAccountShouldNotBeFound("minimumBalance.greaterThanOrEqual=" + UPDATED_MINIMUM_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByMinimumBalanceIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where minimumBalance is less than or equal to DEFAULT_MINIMUM_BALANCE
        defaultVirtualAccountShouldBeFound("minimumBalance.lessThanOrEqual=" + DEFAULT_MINIMUM_BALANCE);

        // Get all the virtualAccountList where minimumBalance is less than or equal to SMALLER_MINIMUM_BALANCE
        defaultVirtualAccountShouldNotBeFound("minimumBalance.lessThanOrEqual=" + SMALLER_MINIMUM_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByMinimumBalanceIsLessThanSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where minimumBalance is less than DEFAULT_MINIMUM_BALANCE
        defaultVirtualAccountShouldNotBeFound("minimumBalance.lessThan=" + DEFAULT_MINIMUM_BALANCE);

        // Get all the virtualAccountList where minimumBalance is less than UPDATED_MINIMUM_BALANCE
        defaultVirtualAccountShouldBeFound("minimumBalance.lessThan=" + UPDATED_MINIMUM_BALANCE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByMinimumBalanceIsGreaterThanSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where minimumBalance is greater than DEFAULT_MINIMUM_BALANCE
        defaultVirtualAccountShouldNotBeFound("minimumBalance.greaterThan=" + DEFAULT_MINIMUM_BALANCE);

        // Get all the virtualAccountList where minimumBalance is greater than SMALLER_MINIMUM_BALANCE
        defaultVirtualAccountShouldBeFound("minimumBalance.greaterThan=" + SMALLER_MINIMUM_BALANCE);
    }


    @Test
    @Transactional
    public void getAllVirtualAccountsByAccountTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where accountType equals to DEFAULT_ACCOUNT_TYPE
        defaultVirtualAccountShouldBeFound("accountType.equals=" + DEFAULT_ACCOUNT_TYPE);

        // Get all the virtualAccountList where accountType equals to UPDATED_ACCOUNT_TYPE
        defaultVirtualAccountShouldNotBeFound("accountType.equals=" + UPDATED_ACCOUNT_TYPE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByAccountTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where accountType not equals to DEFAULT_ACCOUNT_TYPE
        defaultVirtualAccountShouldNotBeFound("accountType.notEquals=" + DEFAULT_ACCOUNT_TYPE);

        // Get all the virtualAccountList where accountType not equals to UPDATED_ACCOUNT_TYPE
        defaultVirtualAccountShouldBeFound("accountType.notEquals=" + UPDATED_ACCOUNT_TYPE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByAccountTypeIsInShouldWork() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where accountType in DEFAULT_ACCOUNT_TYPE or UPDATED_ACCOUNT_TYPE
        defaultVirtualAccountShouldBeFound("accountType.in=" + DEFAULT_ACCOUNT_TYPE + "," + UPDATED_ACCOUNT_TYPE);

        // Get all the virtualAccountList where accountType equals to UPDATED_ACCOUNT_TYPE
        defaultVirtualAccountShouldNotBeFound("accountType.in=" + UPDATED_ACCOUNT_TYPE);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByAccountTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where accountType is not null
        defaultVirtualAccountShouldBeFound("accountType.specified=true");

        // Get all the virtualAccountList where accountType is null
        defaultVirtualAccountShouldNotBeFound("accountType.specified=false");
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where status equals to DEFAULT_STATUS
        defaultVirtualAccountShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the virtualAccountList where status equals to UPDATED_STATUS
        defaultVirtualAccountShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where status not equals to DEFAULT_STATUS
        defaultVirtualAccountShouldNotBeFound("status.notEquals=" + DEFAULT_STATUS);

        // Get all the virtualAccountList where status not equals to UPDATED_STATUS
        defaultVirtualAccountShouldBeFound("status.notEquals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultVirtualAccountShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the virtualAccountList where status equals to UPDATED_STATUS
        defaultVirtualAccountShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        // Get all the virtualAccountList where status is not null
        defaultVirtualAccountShouldBeFound("status.specified=true");

        // Get all the virtualAccountList where status is null
        defaultVirtualAccountShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllVirtualAccountsByAccountHolderIsEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);
        Profile accountHolder = ProfileResourceIT.createEntity(em);
        em.persist(accountHolder);
        em.flush();
        virtualAccount.setAccountHolder(accountHolder);
        virtualAccountRepository.saveAndFlush(virtualAccount);
        Long accountHolderId = accountHolder.getId();

        // Get all the virtualAccountList where accountHolder equals to accountHolderId
        defaultVirtualAccountShouldBeFound("accountHolderId.equals=" + accountHolderId);

        // Get all the virtualAccountList where accountHolder equals to accountHolderId + 1
        defaultVirtualAccountShouldNotBeFound("accountHolderId.equals=" + (accountHolderId + 1));
    }


    @Test
    @Transactional
    public void getAllVirtualAccountsByTransactionRequestIsEqualToSomething() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);
        TransactionRequest transactionRequest = TransactionRequestResourceIT.createEntity(em);
        em.persist(transactionRequest);
        em.flush();
        virtualAccount.setTransactionRequest(transactionRequest);
        virtualAccountRepository.saveAndFlush(virtualAccount);
        Long transactionRequestId = transactionRequest.getId();

        // Get all the virtualAccountList where transactionRequest equals to transactionRequestId
        defaultVirtualAccountShouldBeFound("transactionRequestId.equals=" + transactionRequestId);

        // Get all the virtualAccountList where transactionRequest equals to transactionRequestId + 1
        defaultVirtualAccountShouldNotBeFound("transactionRequestId.equals=" + (transactionRequestId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultVirtualAccountShouldBeFound(String filter) throws Exception {
        restVirtualAccountMockMvc.perform(get("/api/virtual-accounts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(virtualAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID)))
            .andExpect(jsonPath("$.[*].extCustomerId").value(hasItem(DEFAULT_EXT_CUSTOMER_ID)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].availableBalance").value(hasItem(DEFAULT_AVAILABLE_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].holdBalance").value(hasItem(DEFAULT_HOLD_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].minimumBalance").value(hasItem(DEFAULT_MINIMUM_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].accountType").value(hasItem(DEFAULT_ACCOUNT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));

        // Check, that the count call also returns 1
        restVirtualAccountMockMvc.perform(get("/api/virtual-accounts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultVirtualAccountShouldNotBeFound(String filter) throws Exception {
        restVirtualAccountMockMvc.perform(get("/api/virtual-accounts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restVirtualAccountMockMvc.perform(get("/api/virtual-accounts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingVirtualAccount() throws Exception {
        // Get the virtualAccount
        restVirtualAccountMockMvc.perform(get("/api/virtual-accounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVirtualAccount() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        int databaseSizeBeforeUpdate = virtualAccountRepository.findAll().size();

        // Update the virtualAccount
        VirtualAccount updatedVirtualAccount = virtualAccountRepository.findById(virtualAccount.getId()).get();
        // Disconnect from session so that the updates on updatedVirtualAccount are not directly saved in db
        em.detach(updatedVirtualAccount);
        updatedVirtualAccount
            .customerId(UPDATED_CUSTOMER_ID)
            .extCustomerId(UPDATED_EXT_CUSTOMER_ID)
            .currency(UPDATED_CURRENCY)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .availableBalance(UPDATED_AVAILABLE_BALANCE)
            .holdBalance(UPDATED_HOLD_BALANCE)
            .minimumBalance(UPDATED_MINIMUM_BALANCE)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .status(UPDATED_STATUS);
        VirtualAccountDTO virtualAccountDTO = virtualAccountMapper.toDto(updatedVirtualAccount);

        restVirtualAccountMockMvc.perform(put("/api/virtual-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(virtualAccountDTO)))
            .andExpect(status().isOk());

        // Validate the VirtualAccount in the database
        List<VirtualAccount> virtualAccountList = virtualAccountRepository.findAll();
        assertThat(virtualAccountList).hasSize(databaseSizeBeforeUpdate);
        VirtualAccount testVirtualAccount = virtualAccountList.get(virtualAccountList.size() - 1);
        assertThat(testVirtualAccount.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testVirtualAccount.getExtCustomerId()).isEqualTo(UPDATED_EXT_CUSTOMER_ID);
        assertThat(testVirtualAccount.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testVirtualAccount.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testVirtualAccount.getAvailableBalance()).isEqualTo(UPDATED_AVAILABLE_BALANCE);
        assertThat(testVirtualAccount.getHoldBalance()).isEqualTo(UPDATED_HOLD_BALANCE);
        assertThat(testVirtualAccount.getMinimumBalance()).isEqualTo(UPDATED_MINIMUM_BALANCE);
        assertThat(testVirtualAccount.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testVirtualAccount.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingVirtualAccount() throws Exception {
        int databaseSizeBeforeUpdate = virtualAccountRepository.findAll().size();

        // Create the VirtualAccount
        VirtualAccountDTO virtualAccountDTO = virtualAccountMapper.toDto(virtualAccount);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVirtualAccountMockMvc.perform(put("/api/virtual-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(virtualAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VirtualAccount in the database
        List<VirtualAccount> virtualAccountList = virtualAccountRepository.findAll();
        assertThat(virtualAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVirtualAccount() throws Exception {
        // Initialize the database
        virtualAccountRepository.saveAndFlush(virtualAccount);

        int databaseSizeBeforeDelete = virtualAccountRepository.findAll().size();

        // Delete the virtualAccount
        restVirtualAccountMockMvc.perform(delete("/api/virtual-accounts/{id}", virtualAccount.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VirtualAccount> virtualAccountList = virtualAccountRepository.findAll();
        assertThat(virtualAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
