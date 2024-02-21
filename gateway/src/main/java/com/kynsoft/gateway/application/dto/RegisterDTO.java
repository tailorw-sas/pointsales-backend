package com.kynsoft.gateway.application.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

@Value
@RequiredArgsConstructor
@Builder
public class RegisterDTO implements Serializable {

    private static final long serialVersionUID = -1764122752782700800L;
	private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private Set<String> roles;
}
