package com.kynsoft.gateway.application.command.registrySystemUser;

import com.kynsoft.gateway.application.command.registry.*;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegistrySystemUserCommand implements ICommand {
    private Boolean resul;
    private final String username;
    private final String email;
    private final String firstname;
    private final String lastname;
    private final String password;
    private final List<String> roles;

    public RegistrySystemUserCommand(String username, String email, String firstname, String lastname, String password, List<String> roles) {

        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public ICommandMessage getMessage() {
        return new RegistrySystemUserMessage(resul);
    }
}
