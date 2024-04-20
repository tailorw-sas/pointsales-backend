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

    public UpdateDiagnosisCommand(UUID id, String icdCode, String description) {
        this.id = id;
        this.description = description;
        this.icdCode = icdCode;
    }

    public static UpdateDiagnosisCommand fromRequest(UpdateDiagnosisRequest request, UUID id) {
        return new UpdateDiagnosisCommand(id, request.getIcdCode(), request.getDescription());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateDiagnosisMessage(id);
    }
}
