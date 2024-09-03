package com.kynsof.identity.application.command.firebaseToken.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateFirebaseTokenRequest {
    private UUID userId;
    private String token;
}
