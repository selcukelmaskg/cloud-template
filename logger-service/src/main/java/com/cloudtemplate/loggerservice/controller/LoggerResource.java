package com.cloudtemplate.loggerservice.controller;

import com.cloudtemplate.loggerservice.domain.Log;
import com.cloudtemplate.loggerservice.service.LogService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LoggerResource.END_POINT)
public class LoggerResource {
    static final String END_POINT = "/api/logs";

    private LogService logService;

    public LoggerResource(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public Page<Log> help(@QuerydslPredicate(root = Log.class) Predicate predicate, Pageable pageable) {
        return logService.findAll(predicate, pageable);
    }
}
