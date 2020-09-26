package com.jhipster.nigeria.extended.config;

/**
 * Constants for Application URL Request Mappings.
 * @author Kelvin Isievwore
 */
public final class ApplicationUrl {

    public static final String BASE_CONTEXT_URL = "v1";

    public static final String API_CONTEXT_URL = "/api";

    public static final String API_V1_CONTEXT_URL = "/api/v1";

    // REGISTRATION API ENDPOINTS
    public static final String REGISTER_PROFILE = "register";

    public static final String ACTIVATE_PROFILE = "activate";

    public static final String COMPLETE_RESET_PASSWORD = "reset-password/init";

    // PROFILE CONTROLLER ENDPOINTS
    public static final String GET_PROFILE = "/profiles/{profileType}/{profileId}";

    public static final String GET_ALL_AGENT_PROFILES = "/profiles/agent";

    public static final String GET_ALL_ADMIN_PROFILES = "/profiles/admin";

    public static final String GET_ALL_CUSTOMER_PROFILES = "/profiles/customer";

}
