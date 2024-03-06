package com.kynsoft.gateway.infrastructure.keycloak;

public class CustomForbiddenException extends RuntimeException {
    public CustomForbiddenException(String message) {
        super(message);
    }
}
