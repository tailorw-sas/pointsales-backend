package com.kynsof.identity.application.command.userPermisionBusiness.create.lote;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoteCreateUserRoleBusinessCommand implements ICommand {

    private List<LoteUserPermissionBusinessRequest> payload;

    public LoteCreateUserRoleBusinessCommand(List<LoteUserPermissionBusinessRequest> payload) {
        this.payload = List.copyOf(payload);        
    }

    public static LoteCreateUserRoleBusinessCommand fromRequest(LoteCreateUserRoleBusinessRequest request) {
        return new LoteCreateUserRoleBusinessCommand(request.getPayload());
    }

    @Override
    public ICommandMessage getMessage() {
        return new LoteCreateUserRoleBusinessMessage(true);
    }
}