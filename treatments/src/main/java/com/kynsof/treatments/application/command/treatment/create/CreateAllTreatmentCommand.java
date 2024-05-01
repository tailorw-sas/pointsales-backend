package com.kynsof.treatments.application.command.treatment.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateAllTreatmentCommand implements ICommand {

    private List<CreateAllTreatmentRequest> treatments;
    private UUID idExternalConsultation;

    public CreateAllTreatmentCommand(PayloadTreatment payload) {
        this.treatments = payload.getTreatment();
        this.idExternalConsultation = payload.getExternalConsultation();
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateAllTreatmentMessage();
    }
}
