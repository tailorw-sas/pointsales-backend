package com.kynsof.shift.application.command.tunerSpecialties.update;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class UpdateTurnerSpecialtiesRequest {

    private String medicalHistory;
    private String patient;
    private String identification;
    private String resource;//Doctor
    private String service;//Specialties
    private String status;
    private LocalDateTime shiftDateTime;
    private LocalTime consultationTime;
    private String business;
}
