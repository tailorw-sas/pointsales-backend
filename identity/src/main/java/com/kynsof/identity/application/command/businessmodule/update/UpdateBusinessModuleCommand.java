package com.kynsof.identity.application.command.businessmodule.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateBusinessModuleCommand implements ICommand {

    private boolean result;
    private UUID id;
    private UUID idBusiness;
    private List<UUID> modules;

    public UpdateBusinessModuleCommand(UUID id, UUID idBusiness, List<UUID> modules) {
        this.id = id;
        this.idBusiness = idBusiness;
        this.modules = List.copyOf(modules);
    }

    public static UpdateBusinessModuleCommand fromRequest(UpdateBusinessModuleRequest request, UUID id) {
        return new UpdateBusinessModuleCommand(
                id,
                request.getIdBusiness(), 
                request.getModules()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateBusinessModuleMessage(result);
    }
}
