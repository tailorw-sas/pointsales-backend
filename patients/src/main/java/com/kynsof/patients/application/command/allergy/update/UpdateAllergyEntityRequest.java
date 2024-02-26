package com.kynsof.patients.application.command.allergy.update;

import com.kynsof.patients.domain.dto.enumTye.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAllergyEntityRequest {

    private String code;
    private String name;
    private Status status;
}
