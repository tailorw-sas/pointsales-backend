package com.kynsof.patients.application.command.patients.updateadmin;

import com.kynsof.patients.domain.dto.enumTye.DisabilityType;
import com.kynsof.patients.domain.dto.enumTye.GenderType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdatePatientsAdminRequest {

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
    private DisabilityType disabilityType;
}
