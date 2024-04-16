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
    private String title;
    private String description;
    private ContentType type;
    private byte[] picture;

    public CreateAdvertisingContentCommand(String title, String description, ContentType type, byte[] picture) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.type = type;
        this.picture = picture;
    }

    public static CreateAdvertisingContentCommand fromRequest(CreateAdvertisingContentRequest request) {
        return new CreateAdvertisingContentCommand(request.getTitle(), request.getDescription(), request.getType(), request.getPicture());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateAdvertisingContentMessage(id);
    }
}
