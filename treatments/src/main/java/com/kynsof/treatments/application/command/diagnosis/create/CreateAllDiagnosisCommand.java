package com.kynsof.treatments.application.command.diagnosis.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateAllDiagnosisCommand implements ICommand {

    private List<DiagnosisRequest> payload;
    private UUID idExternalConsultation;

    public CreateAllDiagnosisCommand(Payload payload) {
        this.payload = payload.getDiagnosis();
        this.idExternalConsultation = payload.getExternalConsultation();
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateAllDiagnosisMessage();
    }
}
