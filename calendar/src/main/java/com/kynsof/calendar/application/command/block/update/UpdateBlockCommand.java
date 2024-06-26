package com.kynsof.calendar.application.command.block.update;

import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateBlockCommand implements ICommand {

    private UUID id;
    private final String code;
    private String name;
    private EServiceStatus status;

    public UpdateBlockCommand(UUID id, String code, String description, EServiceStatus status) {
        this.id = id;
        this.code = code;
        this.name = description;
        this.status = status;
    }

    public static UpdateBlockCommand fromRequest(UUID id, UpdateBlockRequest request) {
        return new UpdateBlockCommand(id, request.getCode(), request.getName(), request.getStatus());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateBlockMessage(id);
    }
}
