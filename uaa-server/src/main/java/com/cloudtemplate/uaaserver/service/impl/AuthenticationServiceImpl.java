package com.cloudtemplate.uaaserver.service.impl;

import com.cloudtemplate.uaaserver.security.AuthUserDetailsService;
import com.cloudtemplate.uaaserver.security.JwtTokenUtil;
import com.cloudtemplate.uaaserver.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomAuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthUserDetailsService authUserDetailsService;

    public String generateToken(String username, String password) throws Exception {
        authenticate(username, password);

        final UserDetails userDetails = authUserDetailsService
                .loadUserByUsername(username);

        return jwtTokenUtil.generateToken(userDetails);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
