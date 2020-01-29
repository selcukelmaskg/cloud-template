package com.cloudtemplate.shared.dto.uaa;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class LoginResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public LoginResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }
}
