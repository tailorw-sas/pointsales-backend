package com.kynsof.patients.application.command.dependents.create.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDependentContactInfoRequest {
    private String email;
    private String telephone;
    private String address;
    private LocalDate birthdayDate;
    private UUID geographicLocationId;
}
