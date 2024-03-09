package com.kynsof.patients.application.command.patients.create;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
public class CreatePatientsRequest {

    private String identification;
    private String name;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private Double weight;
    private Double height;
    private Boolean hasDisability;
    private Boolean isPregnant;
    private MultipartFile photo;
}
