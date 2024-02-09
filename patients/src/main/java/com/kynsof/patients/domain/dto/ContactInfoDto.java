package com.kynsof.patients.domain.dto;

import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.infrastructure.entity.Patients;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ContactInfoDto {
    private UUID id;
    private Patients patient;

    private String email;

    private String telephone;

    private String address;

    private LocalDate birthdayDate;
    private Status status;

}
