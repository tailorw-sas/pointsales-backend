package com.kynsof.patients.application.command.allergy.update;

import com.kynsof.patients.domain.dto.EStatusPatients;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAllergyRequest {

    private String code;
    private String name;
    private EStatusPatients status;
}
