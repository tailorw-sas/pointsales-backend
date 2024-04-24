package com.kynsof.patients.application.command.patients.createadmin;

import com.kynsof.patients.domain.dto.enumTye.DisabilityType;
import com.kynsof.patients.domain.dto.enumTye.GenderType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePatientsAdminRequest {
    private String identification;
    private GenderType gender;
    private Double weight;
    private Double height;
    private Boolean hasDisability;
    private Boolean isPregnant;
    private byte[] image;
    private int gestationTime;
    private DisabilityType disabilityType;
}
