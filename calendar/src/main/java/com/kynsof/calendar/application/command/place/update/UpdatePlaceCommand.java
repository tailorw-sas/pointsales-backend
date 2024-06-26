package com.kynsof.calendar.application.command.place.update;

import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdatePlaceCommand implements ICommand {

    private UUID id;
    private String name;
    private final String code;
    private EServiceStatus status;
    private final UUID block;

    public UpdatePlaceCommand(UUID id, String name, String code,EServiceStatus status, UUID block) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.status = status;
        this.block = block;

    }

    public static UpdatePlaceCommand fromRequest(UUID id, UpdatePlaceRequest request) {
        return new UpdatePlaceCommand(id, request.getName(), request.getCode(), request.getStatus(),
                request.getBlock());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdatePlaceMessage(id);
    }
}
