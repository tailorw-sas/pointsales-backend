package com.kynsof.rrhh.application.command.device.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateDeviceCommand implements ICommand {

    private UUID id;
    private String serialId;
    private String ip;
    private UUID businessId;

    public CreateDeviceCommand(String serialId, String ip, UUID businessId) {
        this.id = UUID.randomUUID();
        this.serialId = serialId;
        this.ip = ip;
        this.businessId = businessId;
    }

    public static CreateDeviceCommand fromRequest(CreateDeviceRequest request) {
        return new CreateDeviceCommand(
                request.getSerialId(), 
                request.getIp(),
                request.getBusinessId()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateDeviceMessage(id);
    }
}
