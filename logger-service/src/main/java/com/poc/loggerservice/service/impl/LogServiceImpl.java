package com.poc.loggerservice.service.impl;

import com.poc.loggerservice.domain.Log;
import com.poc.loggerservice.domain.QLog;
import com.poc.loggerservice.repository.LogRepository;
import com.poc.loggerservice.service.LogService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogServiceImpl implements LogService {

    private LogRepository repository;

    public LogServiceImpl(LogRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Log log) {
        log.setTime(LocalDateTime.now());
        repository.save(log);
    }

    @Override
    public Page<Log> findAll(Predicate predicate, Pageable pageable) {
        if (predicate == null) {
            predicate = QLog.log.id.ne("");
        }
        return repository.findAll(predicate, pageable);
    }
}