package com.kynsof.patients.application.command.allergy.update;

import com.kynsof.patients.domain.bus.command.ICommand;
import com.kynsof.patients.domain.bus.command.ICommandMessage;
import com.kynsof.patients.domain.dto.EStatusPatients;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class UpdateAllergyCommand implements ICommand {
    private UUID id;
    private String code;
    private String name;
    private EStatusPatients status;


    public UpdateAllergyCommand(UUID id, String code, String name,EStatusPatients  status ) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.status = status;
    }

    public static UpdateAllergyCommand fromRequest(UUID id, UpdateAllergyRequest request) {
        return new UpdateAllergyCommand(id, request.getCode(), request.getName(), request.getStatus());
    }


    @Override
    public ICommandMessage getMessage() {
        return new UpdateAllergyMessage();
    }
}
