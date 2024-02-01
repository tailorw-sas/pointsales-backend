package com.kynsof.patients.application.command.create;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientsRequest {

    private String identification;

    private String name;

    private String lastName;

    private String gender;
}
