package com.kynsoft.gateway.application.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Value
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class LoginDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -6587730290416571673L;
    String username;
    String password;
}
