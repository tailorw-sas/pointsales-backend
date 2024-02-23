package com.kynsoft.gateway.application.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Value
@RequiredArgsConstructor
@Builder
public class RegisterDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1764122752782700800L;
    String username;
    String email;
    String firstname;
    String lastname;
    String password;
    Set<String> roles;
}
