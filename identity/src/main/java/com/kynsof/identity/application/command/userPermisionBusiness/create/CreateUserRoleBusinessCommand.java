package com.kynsof.identity.application.command.userPermisionBusiness.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRoleBusinessCommand implements ICommand {

    private List<UserRoleBusinessRequest> payload;

    public CreateUserRoleBusinessCommand(List<UserRoleBusinessRequest> payload) {
        this.payload = List.copyOf(payload);        
    }

    public static CreateUserRoleBusinessCommand fromRequest(CreateUserRoleBusinessRequest request) {
        return new CreateUserRoleBusinessCommand(request.getPayload());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateUserRoleBusinessMessage(true);
    }
}
