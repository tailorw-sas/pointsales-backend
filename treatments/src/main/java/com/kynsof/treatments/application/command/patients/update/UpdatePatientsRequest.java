package com.kynsof.treatments.application.command.patients.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePatientsRequest {

    private String identification;

    private String name;

    private String lastName;

    private String gender;
}
