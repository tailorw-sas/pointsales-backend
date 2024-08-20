package com.kynsof.calendar.application.command.tunerSpecialties.create;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class CreateTurnerSpecialtiesRequest {

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
