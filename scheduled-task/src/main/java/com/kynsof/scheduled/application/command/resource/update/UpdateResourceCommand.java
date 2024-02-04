package com.kynsof.scheduled.application.command.resource.update;

import com.kynsof.scheduled.domain.dto.EResourceStatus;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommand;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateResourceCommand implements ICommand {

    private UUID id;
    private String picture;
    private String name;
    private String registrationNumber;
    private String language;
    private EResourceStatus status;
    private Boolean expressAppointments;

    public UpdateResourceCommand(UUID id, String name, String picture, String registrationNumber, String language, EResourceStatus status, Boolean expressAppointments) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.registrationNumber = registrationNumber;
        this.language = language;
        this.status = status;
        this.expressAppointments = expressAppointments;
    }

    public static UpdateResourceCommand fromRequest(UpdateResourceRequest request) {
        return new UpdateResourceCommand(request.getId(), request.getName(), request.getPicture(), request.getRegistrationNumber(), request.getLanguage(), request.getStatus(), request.getExpressAppointments());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateResourceMessage(id);
    }
}
