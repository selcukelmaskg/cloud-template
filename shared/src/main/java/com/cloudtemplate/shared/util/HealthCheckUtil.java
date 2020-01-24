package com.cloudtemplate.shared.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import static com.cloudtemplate.shared.constans.ApplicationConstants.HEALTH_CHECK_CONFIG_URI_VARIABLE;
import static com.cloudtemplate.shared.constans.ApplicationConstants.HEALTH_CHECK_DEFAULT_CONFIG_URL;
import static com.cloudtemplate.shared.constans.ApplicationConstants.HEALTH_CHECK_RETRY_COUNT;
import static com.cloudtemplate.shared.constans.ApplicationConstants.HEALTH_CHECK_WAIT_MILLISECOND;

public final class HealthCheckUtil {
    private static final Logger log = LoggerFactory.getLogger(HealthCheckUtil.class);

    public static final RestTemplate restTemplate = new RestTemplate();

    public static void configHealthCheck() {
        for (int i = HEALTH_CHECK_RETRY_COUNT; i > 0; i--) {
            try {
                String systemHealthCheckUri = System.getenv(HEALTH_CHECK_CONFIG_URI_VARIABLE);
                HealthCheckUtil.restTemplate.getForEntity(systemHealthCheckUri == null || systemHealthCheckUri.isEmpty() ? HEALTH_CHECK_DEFAULT_CONFIG_URL : systemHealthCheckUri, Void.class);
                break;
            } catch (Exception e) {
                log.error("Config server is not available !");
                try {
                    Thread.sleep(HEALTH_CHECK_WAIT_MILLISECOND);
                } catch (InterruptedException ex) {
                    log.error("Health check thread sleep exception => {}", ex.getMessage());
                }
            }
        }
    }
}
