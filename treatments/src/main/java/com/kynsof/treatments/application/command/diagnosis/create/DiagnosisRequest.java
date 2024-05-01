package com.kynsof.treatments.application.command.diagnosis.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiagnosisRequest {
    private String icdCode; // Código CIE-10
    private String description;
}
