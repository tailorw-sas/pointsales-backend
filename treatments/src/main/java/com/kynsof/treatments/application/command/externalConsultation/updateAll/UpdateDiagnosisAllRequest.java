package com.kynsof.treatments.application.command.externalConsultation.updateAll;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDiagnosisAllRequest {
    private String icdCode; // Código CIE-10
    private String description;
}
