package com.kynsof.treatments.application.command.diagnosis.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.treatments.application.command.diagnosis.create.DiagnosisRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateDiagnosisCommand implements ICommand {

    private final List<DiagnosisRequest> diagnosis;
    private final UUID idExternalConsultation;

    public UpdateDiagnosisCommand(List<DiagnosisRequest> payload, UUID idExternalConsultation) {
        this.diagnosis = payload;
        this.idExternalConsultation = idExternalConsultation;
    }

    public static UpdateDiagnosisCommand fromRequest(UpdateDiagnosisRequest request) {
        return new UpdateDiagnosisCommand(request.getDiagnosis(), request.getExternalConsultation());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateDiagnosisMessage(true);
    }
}
