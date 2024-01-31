package com.kynsof.patients.application.query.getall;

import com.kynsof.patients.domain.Patients;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PatientsResponse {
    private UUID id;

    private String identification;

    private String name;

    private String lastName;

    private String gender;

    public PatientsResponse(Patients patients) {
        this.id = patients.getId();
        this.identification = patients.getIdentification();
        this.name = patients.getName();
        this.lastName = patients.getLastName();
        this.gender = patients.getGender();
    }

}