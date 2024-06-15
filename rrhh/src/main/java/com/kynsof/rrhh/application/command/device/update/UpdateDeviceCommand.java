package com.kynsof.rrhh.application.command.device.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateDeviceCommand implements ICommand {

    private UUID id;
    private String serialId;
    private String ip;
    private UUID businessId;

    public UpdateDeviceCommand(UUID id, String serialId, String ip, UUID businessId) {
        this.id = id;
        this.serialId = serialId;
        this.ip = ip;
        this.businessId = businessId;
    }

    public static UpdateDeviceCommand fromRequest(UpdateDeviceRequest request, UUID id) {
        return new UpdateDeviceCommand(
                id,
                request.getSerialId(), 
                request.getIp(),
                request.getBusinessId()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateDeviceMessage(id);
    }
}
