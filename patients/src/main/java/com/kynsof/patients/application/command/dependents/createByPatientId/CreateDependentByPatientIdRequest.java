package com.kynsof.patients.application.command.dependents.createByPatientId;

import com.kynsof.patients.domain.dto.enumTye.FamilyRelationship;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateDependentByPatientIdRequest {

    private UUID primeId;
    private UUID patientId;
    private FamilyRelationship familyRelationship;

}
