package com.kynsof.patients.application.command.contactInfo.create;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class CreateContactInfoRequest {

    private UUID patientId;
    private String email;
    private String telephone;
    private String address;
    private LocalDate birthdayDate;
    private UUID geographicLocationId;
}
