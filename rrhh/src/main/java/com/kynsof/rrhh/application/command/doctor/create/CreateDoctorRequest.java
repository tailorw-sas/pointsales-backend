package com.kynsof.rrhh.application.command.doctor.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateDoctorRequest {

    private UUID id;
    private String identification;
    private String email;
    private String name;
    private String lastName;
    private String status;
    private String registerNumber;
    private String language;
    private boolean isExpress;
}