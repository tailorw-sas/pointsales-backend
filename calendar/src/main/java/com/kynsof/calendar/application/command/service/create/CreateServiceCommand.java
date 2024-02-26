package com.kynsof.calendar.application.command.service.create;

import com.kynsof.calendar.domain.dto.EServiceType;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateServiceCommand implements ICommand {

    private UUID id;
    private String picture;
    private String name;
    private String description;
    private EServiceType type;
    private Double normalAppointmentPrice;
    private Double expressAppointmentPrice;

    public CreateServiceCommand(String name, String picture, String description, EServiceType type, Double normalAppointmentPrice, Double expressAppointmentPrice) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.picture = picture;
        this.description = description;
        this.type = type;
        this.normalAppointmentPrice = normalAppointmentPrice;
        this.expressAppointmentPrice = expressAppointmentPrice;
    }

    public static CreateServiceCommand fromRequest(CreateServiceRequest request) {
        return new CreateServiceCommand(request.getName(), request.getPicture(), request.getDescription(), request.getType(), request.getNormalAppointmentPrice(), request.getExpressAppointmentPrice());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateServiceMessage(id);
    }
}
