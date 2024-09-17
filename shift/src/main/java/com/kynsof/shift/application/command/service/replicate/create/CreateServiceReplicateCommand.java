package com.kynsof.shift.application.command.service.replicate.create;

import com.kynsof.shift.domain.dto.enumType.EServiceStatus;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateServiceReplicateCommand implements ICommand {

    private UUID id;
    private String image;
    private String name;
    private String description;
    private UUID type;
    private EServiceStatus status;
    private String code;

    private final boolean preferFlag;
    private final int maxPriorityCount;
    private final int priorityCount;
    private final int currentLoop;
    private final int order;

    public CreateServiceReplicateCommand(UUID id, String name, String picture, String description, UUID serviceTypeId,
                                EServiceStatus status, String code, boolean preferFlag,
                                int maxPriorityCount, int priorityCount, int currentLoop, int order) {

        this.preferFlag = preferFlag;
        this.maxPriorityCount = maxPriorityCount;
        this.priorityCount = priorityCount;
        this.currentLoop = currentLoop;
        this.order = order;
        this.id = id;
        this.name = name;
        this.image = picture;
        this.description = description;
        this.type = serviceTypeId;
        this.status = status;
        this.code = code;
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateServiceReplicateMessage(id);
    }
}
