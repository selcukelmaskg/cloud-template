package com.cloudtemplate.shared.constans;

import java.util.Arrays;
import java.util.List;

public class ApplicationConstants {
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";

    public static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    public static final String DEFAULT_SWAGGER_URL = "/v2/api-docs";
    public static final String KEY_SWAGGER_URL = "swagger_url";

    public static final String ZONE_ID = "Europe/Istanbul";

    public static final List<String> EXCEPT_LOG_METHODS = Arrays.asList("info");

    public static final String ORDER_SUCCESS_MSG = "order.message.success";
    public static final String ORDER_FAIL_CUSTOMER_MSG = "order.message.fail.customer";
    public static final String ORDER_FAIL_ASSET_MSG = "order.message.fail.asset";
    public static final String ORDER_FAIL_CATALOG_MSG = "order.message.fail.catalog";
    public static final String ORDER_FAIL_SYSTEM_MSG = "order.message.fail.system";

    public static final int HEALTH_CHECK_RETRY_COUNT = 15;
    public static final Long HEALTH_CHECK_WAIT_MILLISECOND = 10000L;
    public static final String HEALTH_CHECK_DEFAULT_CONFIG_URL = "http://localhost:8888/actuator/health";
    public static final String HEALTH_CHECK_CONFIG_URI_VARIABLE = "CONFIG_URI";

    public static final String AUTHORIZATION_HEADER = "authorization";

    private ApplicationConstants() {
        throw new IllegalStateException("Utility class");
    }
}
