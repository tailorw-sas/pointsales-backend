package com.kynsoft.gateway.application.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Value
@RequiredArgsConstructor
@Builder
public class RegisterDTO implements Serializable {

    String username;
    String email;
    String firstname;
    String lastname;
    String password;
    List<String> roles;
}
