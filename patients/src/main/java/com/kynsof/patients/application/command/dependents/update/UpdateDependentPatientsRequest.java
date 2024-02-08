package com.kynsof.patients.application.command.dependents.update;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateDependentPatientsRequest {

    private UUID primeId;
    private String identification;
    private String name;
    private String lastName;
    private String gender;
}
