package com.cloudtemplate.loggerservice.service;

import com.cloudtemplate.loggerservice.domain.Log;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LogService {
    void save(Log log);

    Page<Log> findAll(Predicate predicate, Pageable pageable);
}
