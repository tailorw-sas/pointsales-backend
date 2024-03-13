package com.kynsof.patients.application.query.patients.getById;


import com.kynsof.patients.application.query.contactInfo.getall.ContactInfoResponse;
import com.kynsof.patients.domain.dto.PatientByIdDto;
import com.kynsof.patients.domain.dto.enumTye.GenderType;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class PatientByIdResponse implements IResponse {
    private UUID id;

    private String identification;
    private String name;
    private String lastName;
    private GenderType gender;
    private Double weight;
    private Double height;
    private Boolean hasDisability;
    private Boolean isPregnant;
    private String photo;
    private ContactInfoResponse contactInfoResponse;

    public PatientByIdResponse(PatientByIdDto patients) {
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
        if (patients.getContactInfoDto() != null) {
            this.contactInfoResponse = new ContactInfoResponse(patients.getContactInfoDto());
        }
    }

}