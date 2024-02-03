package com.kynsof.patients.application.query.contactInfo.getall;


import com.kynsof.patients.domain.bus.query.IResponse;
import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.PatientDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ContactInfoResponse implements IResponse {
    private UUID id;

    private UUID patientId;

    private String email;

    private String telephone;

    private String address;

    private LocalDate birthdayDate;

    public ContactInfoResponse(ContactInfoDto contactInfoDto) {
        this.id = contactInfoDto.getId();
        this.patientId = contactInfoDto.getPatient().getId();
        this.email = contactInfoDto.getEmail();
        this.telephone = contactInfoDto.getTelephone();
        this.address = contactInfoDto.getAddress();
        this.birthdayDate = contactInfoDto.getBirthdayDate();
    }

}