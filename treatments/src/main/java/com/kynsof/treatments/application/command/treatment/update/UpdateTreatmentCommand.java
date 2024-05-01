package com.kynsof.treatments.application.command.treatment.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.treatments.application.command.treatment.create.CreateAllTreatmentRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateTreatmentCommand implements ICommand {

    private final List<CreateAllTreatmentRequest> payload;
    private final UUID idExternalConsultation;

    public UpdateTreatmentCommand(List<CreateAllTreatmentRequest> payload, UUID idExternalConsultation) {

        this.payload = payload;
        this.idExternalConsultation = idExternalConsultation;
    }

    public static UpdateTreatmentCommand fromRequest(UpdateTreatmentRequest request) {
        return new UpdateTreatmentCommand(
              request.getTreatment(),request.getExternalConsultation()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateTreatmentMessage(true);
    }
}
