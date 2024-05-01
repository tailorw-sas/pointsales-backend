package com.kynsof.treatments.application.command.diagnosis.update;

import com.kynsof.treatments.application.command.diagnosis.create.DiagnosisRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateDiagnosisRequest {
    private UUID externalConsultation;
    private List<DiagnosisRequest> diagnosis;
}
