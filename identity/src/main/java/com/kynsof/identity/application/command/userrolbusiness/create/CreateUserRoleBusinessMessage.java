package com.kynsof.identity.application.command.userrolbusiness.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class CreateUserRoleBusinessMessage implements ICommandMessage {

    private final boolean id;

    private final String command = "CREATE_USER_ROLE_BUSINESS";

    public CreateUserRoleBusinessMessage(boolean id) {
        this.id = id;
    }

}
