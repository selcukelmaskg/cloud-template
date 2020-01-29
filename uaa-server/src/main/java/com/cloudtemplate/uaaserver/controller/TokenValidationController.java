package com.cloudtemplate.uaaserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(TokenValidationController.ENDPOINT)
public class TokenValidationController {
    static final String ENDPOINT = "/api/token-validation";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public String deneme(HttpServletRequest request) {
        System.out.println("asdfasdfasdfasdfasd");
        return "sdfasdf";
    }
}
