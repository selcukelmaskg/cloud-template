package com.poc.shared.constans;

import java.util.Arrays;
import java.util.List;

public class ApplicationConstants {
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";

    public static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    public static final String DEFAULT_SWAGGER_URL = "/v2/api-docs";
    public static final String KEY_SWAGGER_URL = "swagger_url";

    public static final String CUSTOMER_ENDPOINT = "http://www.crmpoc.com/customer";
    public static final String CATALOG_ENDPOINT = "http://www.crmpoc.com/catalog";

    public static final String ZONE_ID = "Europe/Istanbul";

    public static final List<String> EXCEPT_LOG_METHODS = Arrays.asList("info");

    public static final String ORDER_SUCCESS_MSG = "order.message.success";
    public static final String ORDER_FAIL_CUSTOMER_MSG = "order.message.fail.customer";
    public static final String ORDER_FAIL_ASSET_MSG = "order.message.fail.asset";
    public static final String ORDER_FAIL_CATALOG_MSG = "order.message.fail.catalog";
    public static final String ORDER_FAIL_SYSTEM_MSG = "order.message.fail.system";


    private ApplicationConstants() {
        throw new IllegalStateException("Utility class");
    }
}
