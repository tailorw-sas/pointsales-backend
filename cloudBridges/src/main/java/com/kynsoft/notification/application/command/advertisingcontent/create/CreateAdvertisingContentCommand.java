package com.kynsoft.notification.application.command.advertisingcontent.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsoft.notification.domain.dto.ContentType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateAdvertisingContentCommand implements ICommand {

    private UUID id;
    private String name;
    private String description;
    private ContentType type;

    public CreateAdvertisingContentCommand(String name, String description, ContentType type) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public static CreateAdvertisingContentCommand fromRequest(CreateAdvertisingContentRequest request) {
        return new CreateAdvertisingContentCommand(request.getName(), request.getDescription(), request.getType());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateAdvertisingContentMessage(id);
    }
}
