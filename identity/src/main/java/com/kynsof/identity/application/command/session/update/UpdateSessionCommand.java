package com.kynsof.identity.application.command.session.update;

import com.kynsof.identity.domain.dto.enumType.ESessionStatus;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateSessionCommand implements ICommand {

    private UUID id;
    private UUID userSystemId;
    private ESessionStatus status;
    private UUID businessId;

    public UpdateSessionCommand(UUID id, UUID userSystemId,
                                ESessionStatus status, UUID businessId) {
        this.id = id;
        this.userSystemId = userSystemId;
        this.status = status;
        this.businessId = businessId;
    }

    public static UpdateSessionCommand fromRequest(UpdateSessionRequest request,
                                                   UUID id) {
        return new UpdateSessionCommand(
                id,
                request.getUserSystemId(),
                request.getStatus(),
                request.getBusinessId());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateSessionMessage(id);
    }
}
