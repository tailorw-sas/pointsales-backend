package com.kynsof.treatments.application.command.diagnosis.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateDiagnosisCommand implements ICommand {

    private UUID id;
    private String icdCode; // Código CIE-10
    private String description;
    private UUID idExternalConsultation;

    public CreateDiagnosisCommand(String icdCode, String description, UUID idExternalConsultation) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.icdCode = icdCode;
        this.idExternalConsultation = idExternalConsultation;
    }

    public static CreateDiagnosisCommand fromRequest(CreateDiagnosisRequest request) {
        return new CreateDiagnosisCommand(request.getIcdCode(), request.getDescription(), request.getIdExternalConsultation());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateDiagnosisMessage(id);
    }
}
