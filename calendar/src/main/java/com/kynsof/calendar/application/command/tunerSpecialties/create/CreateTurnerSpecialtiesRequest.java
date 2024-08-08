package com.kynsof.calendar.application.command.tunerSpecialties.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTurnerSpecialtiesRequest {

    private String medicalHistory;
    private String patient;
    private String identification;
    private String resource;//Doctor
    private String service;//Specialties
    private String status;
}
