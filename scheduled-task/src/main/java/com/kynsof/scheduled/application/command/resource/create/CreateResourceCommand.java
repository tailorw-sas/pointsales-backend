package com.kynsof.scheduled.application.command.resource.create;

import com.kynsof.scheduled.domain.dto.EResourceStatus;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommand;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateResourceCommand implements ICommand {

    private UUID id;
    private String picture;
    private String name;
    private String registrationNumber;
    private String language;
    private EResourceStatus status;
    private Boolean expressAppointments;

    public CreateResourceCommand(String picture, String name, String registrationNumber, String language, EResourceStatus status, Boolean expressAppointments) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.picture = picture;
        this.registrationNumber = registrationNumber;
        this.language = language;
        this.status = status;
        this.expressAppointments = expressAppointments;
    }

    public static CreateResourceCommand fromRequest(CreateResourceRequest request) {
        return new CreateResourceCommand(request.getPicture(), request.getName(), request.getRegistrationNumber(), request.getLanguage(), request.getStatus(), request.getExpressAppointments());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateResourceMessage(id);
    }
}