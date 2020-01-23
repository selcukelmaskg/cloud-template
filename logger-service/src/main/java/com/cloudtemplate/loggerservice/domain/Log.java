package com.cloudtemplate.loggerservice.domain;

import com.cloudtemplate.loggerservice.domain.base.BaseEntity;
import com.cloudtemplate.shared.constans.LogStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

import static com.cloudtemplate.shared.constans.ApplicationConstants.ZONE_ID;

@Document(collection = "log")
public class Log extends BaseEntity {
    private String methodName;
    private Object request;
    private Object response;
    private String instanceId;
    private LogStatus status;
    private LocalDateTime time;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public LogStatus getStatus() {
        return status;
    }

    public void setStatus(LogStatus status) {
        this.status = status;
    }

    public ZonedDateTime getTime() {
        return ZonedDateTime.of(this.time, ZoneId.of(ZONE_ID));
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Log)) return false;
        if (!super.equals(o)) return false;
        Log log = (Log) o;
        return getMethodName().equals(log.getMethodName()) &&
                Objects.equals(getRequest(), log.getRequest()) &&
                Objects.equals(getResponse(), log.getResponse()) &&
                getInstanceId().equals(log.getInstanceId()) &&
                getStatus() == log.getStatus() &&
                getTime().equals(log.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMethodName(), getRequest(), getResponse(), getInstanceId(), getStatus(), getTime());
    }

    @Override
    public String toString() {
        return "Log{" +
                "methodName='" + methodName + '\'' +
                ", request=" + request +
                ", response=" + response +
                ", instanceId='" + instanceId + '\'' +
                ", status=" + status +
                ", time=" + time +
                '}';
    }
}
