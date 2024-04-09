package com.kynsof.treatments.application.command.vaccine.update;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateVaccineRequest {

    private String identification;
    private String name;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
}
