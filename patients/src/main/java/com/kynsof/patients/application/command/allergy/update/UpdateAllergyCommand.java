package com.kynsof.patients.application.command.allergy.update;

import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateAllergyCommand implements ICommand {
    private UUID id;
    private String code;
    private String name;
    private Status status;


    public UpdateAllergyCommand(UUID id, String code, String name, Status status ) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.status = status;
    }

    public static UpdateAllergyCommand fromRequest(UUID id, UpdateAllergyEntityRequest request) {
        return new UpdateAllergyCommand(id, request.getCode(), request.getName(), request.getStatus());
    }


    @Override
    public ICommandMessage getMessage() {
        return new UpdateAllergyMessage();
    }
}
