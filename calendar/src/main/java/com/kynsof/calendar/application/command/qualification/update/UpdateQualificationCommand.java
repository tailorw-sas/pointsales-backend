package com.kynsof.calendar.application.command.qualification.update;

import com.kynsof.calendar.domain.dto.EQualificationStatus;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateQualificationCommand implements ICommand {

    private UUID id;

    private String description;

    private EQualificationStatus status;

    public UpdateQualificationCommand(UUID id, String description, EQualificationStatus status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public static UpdateQualificationCommand fromRequest(UpdateQualificationRequest request) {
        return new UpdateQualificationCommand(request.getId(), request.getDescription(), request.getStatus());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateQualificationMessage(id);
    }
}