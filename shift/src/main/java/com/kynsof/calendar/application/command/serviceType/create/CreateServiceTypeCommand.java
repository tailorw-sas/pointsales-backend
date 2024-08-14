package com.kynsof.calendar.application.command.serviceType.create;

import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateServiceTypeCommand implements ICommand {

    private UUID id;
    private String name;
    private final String picture;
    private EServiceStatus status;
    private String code;

    public CreateServiceTypeCommand(String name, String picture, EServiceStatus status, String code) {
        this.picture = picture;
        this.id = UUID.randomUUID();
        this.name = name;
        this.status = status;
        this.code = code;
    }

    public static CreateServiceTypeCommand fromRequest(CreateServiceTypeRequest request) {
        return new CreateServiceTypeCommand(request.getName(), request.getImage(), request.getStatus(), request.getCode());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateServiceTypeMessage(id);
    }
}
