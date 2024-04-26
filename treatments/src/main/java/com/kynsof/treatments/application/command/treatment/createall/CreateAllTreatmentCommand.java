package com.kynsof.treatments.application.command.treatment.createall;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAllTreatmentCommand implements ICommand {

    private List<CreateAllTreatmentRequest> payload;
    private UUID idExternalConsultation;

    public CreateAllTreatmentCommand(PayloadTreatment payload) {
        this.payload = payload.getTreatment();
        this.idExternalConsultation = payload.getExternalConsultation();
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateAllTreatmentMessage();
    }
}
