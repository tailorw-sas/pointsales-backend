package com.kynsof.treatments.application.command.services.replicate.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.treatments.domain.dto.enumDto.EServiceStatus;
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


    public CreateServiceReplicateCommand(UUID id, String name, String picture, String description, UUID serviceTypeId,
                                EServiceStatus status, String code) {

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
