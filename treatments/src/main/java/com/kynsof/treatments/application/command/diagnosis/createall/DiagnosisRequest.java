package com.kynsof.treatments.application.command.diagnosis.createall;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiagnosisRequest {
    private String icdCode; // Código CIE-10
    private String description;
}
