package com.kynsof.patients.application.command.allergy.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateAllergyEntityRequest {

    private String code;
    private String name;
    private UUID medicalInformationId;
}
