package com.kynsof.patients.application.command.additionalInfo.update;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class UpdateAdditionalInfoRequest {

    private UUID patientId;

    private String maritalStatus;

    private String occupation;

    private String emergencyContactName;

    private String emergencyContactPhone;
}
