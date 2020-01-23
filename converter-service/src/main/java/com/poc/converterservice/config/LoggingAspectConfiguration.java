package com.poc.converterservice.config;

import com.poc.converterservice.aop.logging.LoggingAspect;
import com.poc.converterservice.bus.LogProducerManager;
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
