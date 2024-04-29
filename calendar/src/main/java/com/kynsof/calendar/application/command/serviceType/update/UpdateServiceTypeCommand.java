package com.kynsof.calendar.application.command.serviceType.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateServiceTypeCommand implements ICommand {

    private UUID id;
    private String name;
    private final String picture;

    public UpdateServiceTypeCommand(UUID id, String description, String picture) {
        this.id = id;
        this.name = description;
        this.picture = picture;
    }

    public static UpdateServiceTypeCommand fromRequest(UUID id, UpdateServiceTypeRequest request) {
        return new UpdateServiceTypeCommand(id, request.getName(), request.getImage() );
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateServiceTypeMessage(id);
    }
}
