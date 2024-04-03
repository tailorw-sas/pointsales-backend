package com.kynsof.identity.application.command.userrolbusiness.create.lote;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class LoteCreateUserRoleBusinessMessage implements ICommandMessage {

    private final boolean id;

    private final String command = "CREATE_USER_ROLE_BUSINESS";

    public LoteCreateUserRoleBusinessMessage(boolean id) {
        this.id = id;
    }

}
