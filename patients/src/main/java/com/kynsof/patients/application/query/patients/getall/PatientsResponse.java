package com.kynsof.patients.application.query.patients.getall;


import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class PatientsResponse implements IResponse {
    private UUID id;

    private String identification;
    private String name;
    private String lastName;
    private String gender;
    private Double weight;
    private Double height;
    private Boolean hasDisability;
    private Boolean isPregnant;
    private String photo;
    public PatientsResponse(PatientDto patients) {
        this.id = patients.getId();
        this.identification = patients.getIdentification();
        this.name = patients.getName();
        this.lastName = patients.getLastName();
        this.gender = patients.getGender();
        this.weight = patients.getWeight();
        this.height = patients.getHeight();
        this.hasDisability = patients.getHasDisability();
        this.isPregnant = patients.getIsPregnant();
        photo = patients.getPhoto();
    }

}