package com.kynsof.treatments.application.command.patients.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePatientsRequest {

    private String identification;

    private String name;

    private String lastName;

    private String gender;
}
