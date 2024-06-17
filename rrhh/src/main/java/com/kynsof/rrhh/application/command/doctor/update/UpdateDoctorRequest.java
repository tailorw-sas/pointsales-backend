package com.kynsof.rrhh.application.command.doctor.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDoctorRequest {
    private String identification;
    private String email;
    private String name;
    private String lastName;
    private String status;
    private String registerNumber;
    private String language;
    private boolean isExpress;
}