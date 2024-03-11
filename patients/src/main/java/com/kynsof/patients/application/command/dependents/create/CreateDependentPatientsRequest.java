package com.kynsof.patients.application.command.dependents.create;

import com.kynsof.patients.application.command.contactInfo.create.CreateContactInfoRequest;
import com.kynsof.patients.domain.dto.enumTye.DisabilityType;
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
    private MultipartFile photo;
    private String identification;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private Double weight;
    private Double height;
    private Boolean hasDisability;
    private DisabilityType disabilityType;
    private FamilyRelationship familyRelationship;
    private Boolean isPregnant;
    private int gestationTime;
    private CreateContactInfoRequest createContactInfoRequest;

}
