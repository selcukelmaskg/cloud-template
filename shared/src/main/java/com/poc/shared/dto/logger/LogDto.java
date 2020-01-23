package com.poc.shared.dto.logger;

import com.poc.shared.constans.LogStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LogDto {
    private String methodName;
    private Object request;
    private Object response;
    private String instanceId;
    private LogStatus status;
    private LocalDateTime time;
}
