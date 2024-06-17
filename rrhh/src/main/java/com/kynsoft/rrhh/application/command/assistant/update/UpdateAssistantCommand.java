package com.kynsoft.rrhh.application.command.assistant.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateAssistantCommand implements ICommand {

    private UUID id;
    private String serialId;
    private String ip;
    private UUID businessId;

    public UpdateAssistantCommand(UUID id, String serialId, String ip, UUID businessId) {
        this.id = id;
        this.serialId = serialId;
        this.ip = ip;
        this.businessId = businessId;
    }

    public static UpdateAssistantCommand fromRequest(UpdateAssistantRequest request, UUID id) {
        return new UpdateAssistantCommand(
                id,
                request.getSerialId(), 
                request.getIp(),
                request.getBusinessId()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateAssistantMessage(id);
    }
}
