package com.poc.customerservice.bus.impl;

import com.poc.customerservice.bus.LogProducerManager;
import com.poc.shared.constans.LogStatus;
import com.poc.shared.dto.logger.LogDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import static com.poc.shared.constans.ApplicationConstants.EXCEPT_LOG_METHODS;

@Service
public class LogProducerManagerImpl implements LogProducerManager {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Value("${kafka.topic.log}")
    private String logTopic;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    private KafkaTemplate<String, Object> kafkaTemplate;

    public LogProducerManagerImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async
    @Override
    public void info(String methodName, Object request, Object respone) {
        this.sendLogEvent(LogDto
                .builder()
                .instanceId(instanceId)
                .methodName(methodName)
                .request(request)
                .response(respone)
                .status(LogStatus.SUCCESS)
                .build());
    }

    @Async
    @Override
    public void error(String methodName, Object request, Object respone) {
        this.sendLogEvent(LogDto
                .builder()
                .instanceId(instanceId)
                .methodName(methodName)
                .request(request)
                .response(respone)
                .status(LogStatus.FAIL)
                .build());
    }

    private void sendLogEvent(LogDto log) {
        LOG.debug("[sendLogEvent] MessageEvent object is sending.. -> {}", log);

        if (!EXCEPT_LOG_METHODS.contains(log.getMethodName())) {
            ListenableFuture<SendResult<String, Object>> future = this.kafkaTemplate.send(logTopic, log);

            future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                @Override
                public void onSuccess(SendResult<String, Object> result) {
                    LOG.debug("[sendLogEvent] sent message='{}' with offset={}", log, result.getRecordMetadata().offset());
                }

                @Override
                public void onFailure(Throwable ex) {
                    LOG.error("[sendLogEvent] unable to send message='{}'", log, ex);
                }

            });
        }
    }
}
