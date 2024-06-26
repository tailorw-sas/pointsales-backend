package com.kynsof.calendar.application.command.place.create;

import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreatePlaceCommand implements ICommand {

    private UUID id;
    private String code;
    private String name;
    private EServiceStatus status;
    private final UUID block;

    public CreatePlaceCommand(String name, EServiceStatus status, String code, UUID block) {
        this.block = block;
        this.id = UUID.randomUUID();
        this.name = name;
        this.status = status;
        this.code = code;
    }

    public static CreatePlaceCommand fromRequest(CreatePlaceRequest request) {
        return new CreatePlaceCommand(request.getName(),  request.getStatus(), request.getCode(),
                request.getBlock());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreatePlaceMessage(id);
    }
}
