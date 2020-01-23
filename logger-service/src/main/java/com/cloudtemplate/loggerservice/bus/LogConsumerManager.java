package com.cloudtemplate.loggerservice.bus;

import com.cloudtemplate.loggerservice.domain.Log;
import com.cloudtemplate.loggerservice.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.cloudtemplate.shared.constans.ApplicationConstants.EXCEPT_LOG_METHODS;

@Service
public class LogConsumerManager {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private LogService logService;

    public LogConsumerManager(LogService logService) {
        this.logService = logService;
    }

    @KafkaListener(topics = "${kafka.topic.log}")
    public void handleBackOfficeEvent(Log log) {
        try {
            if (!EXCEPT_LOG_METHODS.contains(log.getMethodName())) {
                this.log.info("[handleLogEvent]: handleLogEvent is processing. Log:\n {}", log);
                this.logService.save(log);
            }
        }catch (Exception e) {
            System.out.println("error");
        }
    }
}
