package com.kynsof.identity.application.command.userrolbusiness.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateUserRoleBusinessMessage implements ICommandMessage {

    private final boolean id;

    private final String command = "UPDATE_USER_PERMISSION_BUSINESS";

    public UpdateUserRoleBusinessMessage(boolean id) {
        this.id = id;
    }

}
