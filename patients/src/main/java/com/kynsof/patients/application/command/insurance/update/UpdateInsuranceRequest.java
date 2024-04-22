package com.kynsof.patients.application.command.insurance.update;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateInsuranceRequest {
    private String name;
    private String description;
}
