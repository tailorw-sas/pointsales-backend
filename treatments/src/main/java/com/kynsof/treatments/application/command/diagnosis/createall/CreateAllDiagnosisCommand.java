package com.kynsof.treatments.application.command.diagnosis.createall;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAllDiagnosisCommand implements ICommand {

    private List<DiagnosisRequest> payload;
    private UUID idExternalConsultation;

    public CreateAllDiagnosisCommand(Payload payload) {
        this.payload = payload.getDiagnosis();
        this.idExternalConsultation = payload.getIdExternalConsultation();
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateAllDiagnosisMessage();
    }
}
