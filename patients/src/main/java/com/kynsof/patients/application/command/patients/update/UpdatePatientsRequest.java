package com.kynsof.patients.application.command.patients.update;

import com.kynsof.patients.application.command.patients.create.request.CreatePatientContactInfoRequest;
import com.kynsof.patients.domain.dto.enumTye.DisabilityType;
import com.kynsof.patients.domain.dto.enumTye.GenderType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePatientsRequest {

    private String identification;
    private String name;
    private String lastName;
    private GenderType gender;
    private Double weight;
    private Double height;
    private Boolean hasDisability;
    private Boolean isPregnant;
    private byte[] photo;
    private int gestationTime;
    private CreatePatientContactInfoRequest contactInfo;
    private DisabilityType disabilityType;
}
