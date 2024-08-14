package com.kynsof.calendar.application.command.tunerSpecialties.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTurnerSpecialtiesRequest {

    private String medicalHistory;
    private String patient;
    private String identification;
    private String resource;//Doctor
    private String service;//Specialties
    private String status;
}
