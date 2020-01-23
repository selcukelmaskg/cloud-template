package com.cloudtemplate.eurekaserver.bus;

public interface LogProducerManager {
    void info(String methodName, Object request, Object respone);

    void error(String methodName, Object request, Object respone);
}
