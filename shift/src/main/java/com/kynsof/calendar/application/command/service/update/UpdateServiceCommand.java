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
    private String description;
    private EServiceStatus status;

    private final boolean preferFlag;
    private final int maxPriorityCount;
    private final int priorityCount;
    private final int currentLoop;
    private final int order;


    public UpdateServiceCommand(UUID id, UUID serviceTypeId, String picture, String name,
                                String description, EServiceStatus status,
                                boolean preferFlag, int maxPriorityCount, int priorityCount,
                                int currentLoop, int order) {
        this.id = id;
        this.serviceTypeId = serviceTypeId;
        this.picture = picture;
        this.name = name;

        this.description = description;
        this.status = status;
        this.preferFlag = preferFlag;
        this.maxPriorityCount = maxPriorityCount;
        this.priorityCount = priorityCount;
        this.currentLoop = currentLoop;
        this.order = order;
    }


    public static UpdateServiceCommand fromRequest(UUID id, UpdateServiceRequest request) {
        return new UpdateServiceCommand(id, request.getType(), request.getImage(),
                request.getName(),

                request.getDescription(),
               request.getStatus(),
                request.isPreferFlag(),
                request.getMaxPriorityCount(),
                request.getPriorityCount(),
                request.getCurrentLoop(),
                request.getOrder());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateServiceMessage(id);
    }
}
