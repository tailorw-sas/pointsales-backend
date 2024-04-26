package com.kynsof.treatments.application.command.treatment.createall;

import com.kynsof.treatments.application.command.treatment.create.CreateTreatmentRequest;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PayloadTreatment {
    private UUID externalConsultation;
    private List<CreateTreatmentRequest> treatment;
}
