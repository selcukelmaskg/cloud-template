package com.cloudtemplate.uaaserver.service;

public interface AuthenticationService {
    String generateToken(String username, String password) throws Exception;
}
