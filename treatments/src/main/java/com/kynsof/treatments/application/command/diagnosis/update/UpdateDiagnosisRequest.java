package com.kynsof.treatments.application.command.diagnosis.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDiagnosisRequest {
    private String icdCode; // Código CIE-10
    private String description;
}
