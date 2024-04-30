package com.kynsof.calendar.application.command.resource.update;

import com.kynsof.calendar.domain.dto.enumType.EResourceStatus;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateResourceCommand implements ICommand {

    private UUID id;
    private String registrationNumber;
    private String language;
    private EResourceStatus status;
    private Boolean expressAppointments;
    private String identification;

    public UpdateResourceCommand(UUID id,String registrationNumber, String language, EResourceStatus status, Boolean expressAppointments, String identification) {
        this.id = id;

        this.registrationNumber = registrationNumber;
        this.language = language;
        this.status = status;
        this.expressAppointments = expressAppointments;
        this.identification = identification;
    }

    public static UpdateResourceCommand fromRequest(UUID id, UpdateResourceRequest request) {
        return new UpdateResourceCommand(id,  request.getRegistrationNumber(), request.getLanguage(),
                request.getStatus(), request.getExpressAppointments(), request.getIdentification());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateResourceMessage(id);
    }
}
