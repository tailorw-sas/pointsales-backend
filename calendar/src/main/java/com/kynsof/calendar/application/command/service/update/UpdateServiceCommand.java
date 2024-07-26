package com.kynsof.calendar.application.command.service.update;

import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateServiceCommand implements ICommand {

    private UUID id;
    private UUID serviceTypeId;
    private String picture;
    private String name;
    private Double normalAppointmentPrice;
    private Double expressAppointmentPrice;
    private String description;
    private boolean applyIva;
    private EServiceStatus status;
    private Integer estimatedDuration;
    private final Integer priority;

    public UpdateServiceCommand(UUID id, UUID serviceTypeId, String picture, String name, Double normalAppointmentPrice,
                                Double expressAppointmentPrice, String description, boolean applyIva,
                                EServiceStatus status, Integer estimatedDuration, Integer priority) {
        this.id = id;
        this.serviceTypeId = serviceTypeId;
        this.picture = picture;
        this.name = name;
        this.normalAppointmentPrice = normalAppointmentPrice;
        this.expressAppointmentPrice = expressAppointmentPrice;
        this.description = description;
        this.applyIva = applyIva;
        this.status = status;
        this.estimatedDuration = estimatedDuration;
        this.priority = priority;
    }


    public static UpdateServiceCommand fromRequest(UUID id, UpdateServiceRequest request) {
        return new UpdateServiceCommand(id, request.getType(), request.getImage(),
                request.getName(), request.getNormalAppointmentPrice(), request.getExpressAppointmentPrice(),
                request.getDescription(), request.isApplyIva(), request.getStatus(), request.getEstimatedDuration(),
                request.getPriority());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateServiceMessage(id);
    }
}
