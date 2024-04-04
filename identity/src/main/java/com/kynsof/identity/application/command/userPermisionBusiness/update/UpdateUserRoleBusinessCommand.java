package com.kynsof.identity.application.command.userPermisionBusiness.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRoleBusinessCommand implements ICommand {

    private List<UserRoleBusinessUpdateRequest> payload;

    public UpdateUserRoleBusinessCommand(List<UserRoleBusinessUpdateRequest> payload) {
        this.payload = List.copyOf(payload);        
    }

    public static UpdateUserRoleBusinessCommand fromRequest(UpdateUserRoleBusinessRequest request) {
        return new UpdateUserRoleBusinessCommand(request.getPayload());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateUserRoleBusinessMessage(true);
    }
}
