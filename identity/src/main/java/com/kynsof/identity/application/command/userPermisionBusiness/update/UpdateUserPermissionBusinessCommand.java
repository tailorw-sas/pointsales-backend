package com.kynsof.identity.application.command.userPermisionBusiness.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserPermissionBusinessCommand implements ICommand {

    private List<UserRoleBusinessUpdateRequest> payload;

    public UpdateUserPermissionBusinessCommand(List<UserRoleBusinessUpdateRequest> payload) {
        this.payload = List.copyOf(payload);        
    }

    public static UpdateUserPermissionBusinessCommand fromRequest(UpdateUserPermissionBusinessRequest request) {
        return new UpdateUserPermissionBusinessCommand(request.getPayload());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateUserPermissionBusinessMessage(true);
    }
}
