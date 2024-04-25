package com.kynsoft.gateway.application.command.auth.registrySystemUser;

import com.kynsof.share.core.domain.UserType;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RegistrySystemUserCommand implements ICommand {
    private UUID id;
    private final String username;
    private final String email;
    private final String firstname;
    private final String lastname;
    private final String password;
    private final List<String> roles;
    private final UserType userType;

    public RegistrySystemUserCommand(String username, String email, String firstname, String lastname, String password,
                                     List<String> roles, UserType userType) {

        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.roles = roles;
        this.userType = userType;
    }

    @Override
    public ICommandMessage getMessage() {
        return new RegistrySystemUserMessage(id);
    }
}
