package com.kynsof.treatments.application.command.treatment.create;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class PayloadTreatment {
    private UUID externalConsultation;
    private List<CreateAllTreatmentRequest> treatment;
}
