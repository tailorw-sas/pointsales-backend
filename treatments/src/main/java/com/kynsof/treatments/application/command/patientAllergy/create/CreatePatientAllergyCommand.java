package com.kynsof.treatments.application.command.patientAllergy.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreatePatientAllergyCommand implements ICommand {

    private final UUID patientId;
    private final String cie10;
    private final String observations;
    private UUID allergyId;
    private final String status;

    public CreatePatientAllergyCommand(UUID patientId, String cie10Id, String observations, String status) {
        this.patientId = patientId;
        this.cie10 = cie10Id;
        this.observations = observations;
        this.status = status;
    }

    public static CreatePatientAllergyCommand fromRequest(CreatePatientAllergyRequest request) {
        return new CreatePatientAllergyCommand(request.getPatientId(), request.getCie10(),
                request.getObservations(),request.getStatus() );
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreatePatientAllergyMessage(allergyId);
    }
}
