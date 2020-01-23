package com.poc.orderservice.config;

import com.poc.orderservice.aop.logging.LoggingAspect;
import com.poc.orderservice.bus.LogProducerManager;
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
