package com.kynsof.treatments.application.command.diagnosis.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateDiagnosisCommand implements ICommand {

    private UUID id;
    private String icdCode; // Código CIE-10
    private String description;
    private UUID idExternalConsultation;

    public UpdateDiagnosisCommand(UUID id, String icdCode, String description, UUID idExternalConsultation) {
        this.id = id;
        this.description = description;
        this.icdCode = icdCode;
        this.idExternalConsultation = idExternalConsultation;
    }

    public static UpdateDiagnosisCommand fromRequest(UpdateDiagnosisRequest request, UUID id) {
        return new UpdateDiagnosisCommand(id, request.getIcdCode(), request.getDescription(), request.getExternalConsultation());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateDiagnosisMessage(id);
    }
}
