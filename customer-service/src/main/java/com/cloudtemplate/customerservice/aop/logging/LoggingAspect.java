package com.cloudtemplate.customerservice.aop.logging;

import com.cloudtemplate.customerservice.bus.LogProducerManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
    private final LogProducerManager log;

    public LoggingAspect(LogProducerManager log) {
        this.log = log;
    }

    @Pointcut("within(@org.springframework.ws.server.endpoint.annotation.Endpoint *)")
    public void springBeanPointcut() {
    }

    @AfterThrowing(pointcut = "springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        this.log.error(joinPoint.getSignature().getName(), joinPoint.getArgs(), e.getMessage());
    }

    @AfterReturning(pointcut = "springBeanPointcut()", returning = "returnValue")
    public void logAround(JoinPoint joinPoint, Object returnValue) {
        this.log.info(joinPoint.getSignature().getName(), joinPoint.getArgs(), returnValue == null ? "" : returnValue.toString());
    }
}
