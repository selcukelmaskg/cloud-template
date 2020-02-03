package com.cloudtemplate.uaaserver.service.impl;

import com.cloudtemplate.uaaserver.util.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

@Service
public class CustomAuthenticationManager implements AuthenticationManager {
    @Autowired
    private PasswordEncoder encoder;

    @Bean
    private PasswordEncoder encoder(){
        return new CryptoUtils();
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

//      call userAccessor and get user password
        if (!encoder.matches(password, "2c9341ca4cf3d87b9e4eb905d6a3ec45")) {
            throw new BadCredentialsException("User not found with username: ");
        }

        return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
    }

    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }
}
