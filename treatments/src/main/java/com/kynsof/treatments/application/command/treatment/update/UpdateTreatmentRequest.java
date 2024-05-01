package com.kynsof.treatments.application.command.treatment.update;

import com.kynsof.treatments.application.command.treatment.create.CreateAllTreatmentRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateTreatmentRequest {
    private UUID externalConsultation;
    private List<CreateAllTreatmentRequest> treatment;
}
