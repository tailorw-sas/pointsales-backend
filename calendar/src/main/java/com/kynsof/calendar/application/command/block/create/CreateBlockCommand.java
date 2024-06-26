package com.kynsof.calendar.application.command.block.create;

import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateBlockCommand implements ICommand {

    private UUID id;
    private String name;
    private EServiceStatus status;
    private String code;
    private final UUID business;

    public CreateBlockCommand(String name, EServiceStatus status, String code, UUID business) {
        this.business = business;

        this.id = UUID.randomUUID();
        this.name = name;
        this.status = status;
        this.code = code;
    }

    public static CreateBlockCommand fromRequest(CreateBlockRequest request) {
        return new CreateBlockCommand(request.getName(),  request.getStatus(), request.getCode(),
                request.getBusiness());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateBlockMessage(id);
    }
}
