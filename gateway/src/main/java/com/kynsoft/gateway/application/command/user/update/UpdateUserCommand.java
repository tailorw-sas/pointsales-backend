package com.kynsoft.gateway.application.command.user.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateUserCommand implements ICommand {
    private Boolean resul;
    private final String userId;
    private final String username;
    private final String email;
    private final String firstname;
    private final String lastname;
    private final String password;
    private final  List<String> roles;


    public UpdateUserCommand(String userId, String username, String email, String firstname, String lastname, String password, List<String> roles) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateUserMessage(resul);
    }
}
