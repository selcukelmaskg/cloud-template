package com.cloudtemplate.uaaserver.controller;

import com.cloudtemplate.shared.dto.uaa.LoginRequest;
import com.cloudtemplate.shared.dto.uaa.LoginResponse;
import com.cloudtemplate.uaaserver.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthenticationController.ENDPOINT)
public class AuthenticationController {
    static final String ENDPOINT = "/api/login";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        LoginResponse response = new LoginResponse(authenticationService.generateToken(loginRequest.getUsername(), loginRequest.getPassword()));
        log.info("Successfully login -> {}", loginRequest.getUsername());
        return ResponseEntity.ok().body(response);
    }
}
