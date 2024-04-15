package com.kynsof.calendar.application.command.service.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateServiceCommand implements ICommand {

    private UUID id;
    private byte[] image;
    private String name;
    private String description;
    private UUID type;
    private Double normalAppointmentPrice;
    private Double expressAppointmentPrice;
    private boolean applyIva;

    public CreateServiceCommand(String name, byte[] picture, String description, UUID serviceTypeId,
                                Double normalAppointmentPrice, Double expressAppointmentPrice, Boolean applyIva) {
        this.applyIva = applyIva;
        this.id = UUID.randomUUID();
        this.name = name;
        this.image = picture;
        this.description = description;
        this.type = serviceTypeId;
        this.normalAppointmentPrice = normalAppointmentPrice;
        this.expressAppointmentPrice = expressAppointmentPrice;
    }

    public static CreateServiceCommand fromRequest(CreateServiceRequest request) {
        return new CreateServiceCommand(request.getName(), request.getImage(), request.getDescription(),
                request.getType(), request.getNormalAppointmentPrice(), request.getExpressAppointmentPrice(),request.isApplyIva() );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateServiceMessage(id);
    }
}
