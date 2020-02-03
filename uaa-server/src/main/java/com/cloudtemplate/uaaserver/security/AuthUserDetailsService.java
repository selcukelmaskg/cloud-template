package com.cloudtemplate.uaaserver.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //call userserviceAccessor for username and password properties
        //feign client customer service
        if ("testnihal".equals(username)) {
            Set<SimpleGrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
            return new User("testnihal", "2c9341ca4cf3d87b9e4eb905d6a3ec45", authorities);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
