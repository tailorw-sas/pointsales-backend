package com.kynsof.treatments.application.command.diagnosis.create;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDiagnosisRequest {
    private String icdCode; // Código CIE-10
    private String description;
    private UUID idExternalConsultation;
}
