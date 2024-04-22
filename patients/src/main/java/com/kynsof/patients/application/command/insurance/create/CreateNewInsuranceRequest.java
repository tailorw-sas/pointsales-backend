package com.kynsof.patients.application.command.insurance.create;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateNewInsuranceRequest {
    private String name;
    private String description;
}
