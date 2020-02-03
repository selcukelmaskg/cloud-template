package com.cloudtemplate.uaaserver.config;

import com.cloudtemplate.uaaserver.util.CryptoUtils;
import org.postgresql.shaded.com.ongres.scram.common.util.CryptoUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfigurer {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CryptoUtils();
    }

    @Primary
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
