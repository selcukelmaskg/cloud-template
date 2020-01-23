package com.poc.eurekaserver.config;

import com.poc.eurekaserver.aop.logging.LoggingAspect;
import com.poc.eurekaserver.bus.LogProducerManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

    @Bean
    public LoggingAspect loggingAspect(LogProducerManager logProducerManager) {
        return new LoggingAspect(logProducerManager);
    }
}
