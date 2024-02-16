package com.kynsof.calendar.application.command.business.create;

import com.kynsof.calendar.domain.dto.EBusinessStatus;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateBusinessCommand implements ICommand {

    private UUID id;
    private String name;
    private String description;
    private byte[] logo;
    private String ruc;
    private EBusinessStatus status;

    public CreateBusinessCommand(String name, String description, byte[] logo, String ruc, EBusinessStatus status) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.ruc = ruc;
        this.status = status;
    }

    public static CreateBusinessCommand fromRequest(CreateBusinessRequest request) {
        return new CreateBusinessCommand(request.getName(), request.getDescription(), request.getLogo(), request.getRuc(), request.getStatus());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateBusinessMessage(id);
    }
}
