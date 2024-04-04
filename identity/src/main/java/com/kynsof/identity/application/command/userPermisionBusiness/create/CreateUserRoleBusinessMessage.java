package com.kynsof.identity.application.command.userPermisionBusiness.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class CreateUserRoleBusinessMessage implements ICommandMessage {

    private final boolean result;

    private final String command = "CREATE_USER_PERMISSION_BUSINESS";

    public CreateUserRoleBusinessMessage(boolean result) {
        this.result = result;
    }

}
