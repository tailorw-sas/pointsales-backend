package com.kynsof.patients.application.command.contactInfo.update;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class UpdateContactInfoRequest {

    private UUID patientId;

    private String email;

    private String telephone;

    private String address;

    private LocalDate birthdayDate;
}