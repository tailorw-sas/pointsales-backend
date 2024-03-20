package com.kynsoft.gateway.application.command.auth.autenticate;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsoft.gateway.application.dto.TokenResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticateCommand implements ICommand {
    private TokenResponse tokenResponse;
    private String userName;
    private String password;



    public AuthenticateCommand( String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

//    public static AuthenticateCommand fromRequest(CreateAllergyEntityRequest request) {
//        return new AuthenticateCommand(request.getMedicalInformationId(), request.getCode(), request.getName());
//    }


    @Override
    public ICommandMessage getMessage() {
        return new AuthenticateMessage(tokenResponse);
    }
}
