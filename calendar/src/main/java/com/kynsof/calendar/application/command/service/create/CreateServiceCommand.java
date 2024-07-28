package com.kynsof.calendar.application.command.service.create;

import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateServiceCommand implements ICommand {

    private UUID id;
    private String image;
    private String name;
    private String description;
    private UUID type;
    private Double normalAppointmentPrice;
    private Double expressAppointmentPrice;
    private boolean applyIva;
    private EServiceStatus status;
    private Integer estimatedDuration;
    private String code;

    private final boolean preferFlag;
    private final int maxPriorityCount;
    private final int priorityCount;
    private final int currentLoop;
    private final int order;

    public CreateServiceCommand(String name, String picture, String description, UUID serviceTypeId,
                                Double normalAppointmentPrice, Double expressAppointmentPrice, Boolean applyIva,
                                EServiceStatus status, Integer estimatedDuration, String code, boolean preferFlag, int maxPriorityCount, int priorityCount, int currentLoop, int order) {
        this.applyIva = applyIva;
        this.preferFlag = preferFlag;
        this.maxPriorityCount = maxPriorityCount;
        this.priorityCount = priorityCount;
        this.currentLoop = currentLoop;
        this.order = order;
        this.id = UUID.randomUUID();
        this.name = name;
        this.image = picture;
        this.description = description;
        this.type = serviceTypeId;
        this.normalAppointmentPrice = normalAppointmentPrice;
        this.expressAppointmentPrice = expressAppointmentPrice;
        this.status = status;
        this.estimatedDuration = estimatedDuration;
        this.code = code;
    }

    public static CreateServiceCommand fromRequest(CreateServiceRequest request) {
        return new CreateServiceCommand(request.getName(), request.getImage(), request.getDescription(),
                request.getType(), request.getNormalAppointmentPrice(), request.getExpressAppointmentPrice(),
                request.isApplyIva(), request.getStatus(), request.getEstimatedDuration(), request.getCode(),
                request.isPreferFlag(),
                request.getMaxPriorityCount(),
                request.getPriorityCount(),
                request.getCurrentLoop(),
                request.getOrder());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateServiceMessage(id);
    }
}
