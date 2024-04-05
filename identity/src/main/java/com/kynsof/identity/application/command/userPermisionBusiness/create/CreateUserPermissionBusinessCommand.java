package com.kynsof.identity.application.command.userPermisionBusiness.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserPermissionBusinessCommand implements ICommand {

    private List<UserPermissionBusinessRequest> payload;

    public CreateUserPermissionBusinessCommand(List<UserPermissionBusinessRequest> payload) {
        this.payload = List.copyOf(payload);        
    }

    public static CreateUserPermissionBusinessCommand fromRequest(CreateUserPermissionBusinessRequest request) {
        return new CreateUserPermissionBusinessCommand(request.getPayload());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateUserPermissionBusinessMessage(true);
    }
}
