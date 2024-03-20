package com.kynsoft.gateway.application.command.auth.autenticate;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsoft.gateway.application.dto.TokenResponse;
import lombok.Getter;

@Getter
public class AuthenticateMessage implements ICommandMessage {

    private final TokenResponse tokenResponse;
    private final String command = "AUTHENTICATE";

    public AuthenticateMessage(TokenResponse token) {
        tokenResponse = token;
    }

}
