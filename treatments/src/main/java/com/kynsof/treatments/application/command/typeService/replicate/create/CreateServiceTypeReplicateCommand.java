package com.kynsof.treatments.application.command.typeService.replicate.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.treatments.domain.dto.enumDto.EServiceStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateServiceTypeReplicateCommand implements ICommand {

    private UUID id;
    private String name;
    private final String picture;
    private EServiceStatus status;
    private String code;

    public CreateServiceTypeReplicateCommand(UUID id, String name, String picture, EServiceStatus status, String code) {
        this.picture = picture;
        this.id = id;
        this.name = name;
        this.status = status;
        this.code = code;
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateServiceTypeReplicateMessage(id);
    }
}
