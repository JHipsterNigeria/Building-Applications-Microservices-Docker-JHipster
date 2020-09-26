package com.jhipster.nigeria.extended.web.rest;

import com.jhipster.nigeria.extended.client.UaaFeignClient;
import com.jhipster.nigeria.extended.service.AccountService;
import com.jhipster.nigeria.service.ProfileQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class BaseAccountController {

    public static final String TOTAL_NUMBER_OF_PAGES = "totalNumberOfPages";
    public static final String TOTAL_NUMBER_OF_RECORDS = "totalNumberOfRecords";
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String PAGE_SIZE = "pageSize";
    public static final String PROFILES = "profiles";
    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 100;


    @Value("${max.page.size:100}")
    protected int maxRecords;

    @Autowired
    protected AccountService accountService;


    @Value("${default.customer.account.bankcode:598}")
    protected String defaultBankCode;


    @Value("${default.api.dateFormat:ddMMyyyy}")
    protected String dateFormat;



    @Autowired
    protected ProfileQueryService profileQueryService;

    @Autowired
    protected UaaFeignClient uaaFeignClient;

}
