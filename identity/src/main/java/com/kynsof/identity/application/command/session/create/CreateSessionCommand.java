package com.kynsof.identity.application.command.session.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateSessionCommand implements ICommand {

    private UUID id;
    private UUID userSystemId;
    private UUID businessId;

    public CreateSessionCommand(UUID userSystemId, UUID businessId) {
        this.id = UUID.randomUUID();
        this.userSystemId = userSystemId;
        this.businessId = businessId;
    }

    public static CreateSessionCommand fromRequest(CreateSessionRequest request) {
        return new CreateSessionCommand(
                request.getUserSystemId(),
                request.getBusinessId()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateSessionMessage(id);
    }
}
