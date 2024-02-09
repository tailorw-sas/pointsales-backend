package com.kynsof.patients.application.query.patients.getall;


import com.kynsof.patients.domain.bus.query.IResponse;

import java.time.LocalDate;
import java.util.UUID;

import com.kynsof.patients.domain.dto.PatientDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PatientsResponse implements IResponse {
    private UUID id;

    private String identification;
    private String name;
    private String lastName;
    private String gender;
    public PatientsResponse(PatientDto patients) {
        this.id = patients.getId();
        this.identification = patients.getIdentification();
        this.name = patients.getName();
        this.lastName = patients.getLastName();
        this.gender = patients.getGender();
    }

}