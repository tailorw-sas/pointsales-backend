package com.kynsof.patients.application.command.additionalInfo.create;

import com.kynsof.patients.infrastructure.entity.Patients;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class CreateAdditionalInfoRequest {

    private UUID patientId;

    private String maritalStatus;

    private String occupation;

    private String emergencyContactName;

    private String emergencyContactPhone;
}
