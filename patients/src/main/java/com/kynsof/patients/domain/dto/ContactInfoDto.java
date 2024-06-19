package com.kynsof.patients.domain.dto;

import com.kynsof.patients.domain.dto.enumTye.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoDto implements Serializable {
    private UUID id;
    private PatientDto patient;
    private String email;
    private String telephone;
    private String address;
    private LocalDate birthdayDate;
    private Status status;
    private GeographicLocationDto province;
    private GeographicLocationDto canton;
    private GeographicLocationDto parroquia;

}
