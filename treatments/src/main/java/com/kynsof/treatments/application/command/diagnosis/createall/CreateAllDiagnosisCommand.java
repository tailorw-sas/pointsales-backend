package com.kynsof.treatments.application.command.diagnosis.createall;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAllDiagnosisCommand implements ICommand {

    private List<DiagnosisRequest> payload;

    public CreateAllDiagnosisCommand(Payload payload) {
        this.payload = payload.getPayload();
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateAllDiagnosisMessage();
    }
}
