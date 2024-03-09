package com.kynsof.patients.application.command.dependents.create;

import com.kynsof.patients.domain.dto.enumTye.FamilyRelationship;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class CreateDependentPatientsRequest {

    private UUID primeId;
    private String identification;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private Double weight;
    private Double height;
    private Boolean hasDisability;
    private Boolean isPregnant;
    private FamilyRelationship familyRelationship;
    private MultipartFile photo;
}
