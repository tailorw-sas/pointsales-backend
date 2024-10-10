package com.kynsof.patients.domain.dto;

import com.kynsof.patients.domain.dto.enumTye.DisabilityType;
import com.kynsof.patients.domain.dto.enumTye.GenderType;
import com.kynsof.patients.domain.dto.enumTye.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;


@Getter
@Setter
public class PatientDto implements Serializable {

    private final UUID id;
    private final String identification;
    private final String name;
    private final String lastName;
    private final GenderType gender;
    private final Status status;
    private final Double weight;
    private final Double height;
    private final Boolean hasDisability;
    private final Boolean isPregnant;
    private final String photo;
    private final DisabilityType disabilityType;
    private final int gestationTime;

    private ContactInfoDto contactInfo;

    public PatientDto(UUID id, String identification, String name, String lastName, GenderType gender, Status status,
                      Double weight, Double height, Boolean hasDisability, Boolean isPregnant, String photo,
                      DisabilityType disabilityType, int gestationTime) {
        this.id = id;
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.status = status;
        this.weight = weight;
        this.height = height;
        this.hasDisability = hasDisability;
        this.isPregnant = isPregnant;
        this.photo = photo;
        this.disabilityType = disabilityType;
        this.gestationTime = gestationTime;
    }
}
