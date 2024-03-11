package com.kynsof.patients.application.command.patients.update;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpdatePatientsRequest {

    private String identification;
    private String name;
    private String lastName;
    private String gender;
    private Double weight;
    private Double height;
    private Boolean hasDisability;
    private Boolean isPregnant;
    private MultipartFile photo;
}
