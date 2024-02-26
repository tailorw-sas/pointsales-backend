package com.kynsof.patients.application.query.additionalInfo.getall;


import com.kynsof.patients.domain.dto.AdditionalInformationDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class AdditionalInfoResponse implements IResponse {
    private UUID id;

    private UUID patientId;

    private String maritalStatus;

    private String occupation;

    private String emergencyContactName;

    private String emergencyContactPhone;
    private Status status;

    public AdditionalInfoResponse(AdditionalInformationDto contactInfoDto) {
        this.id = contactInfoDto.getId();
        this.patientId = contactInfoDto.getPatient().getId();
        this.maritalStatus = contactInfoDto.getMaritalStatus();
        this.occupation = contactInfoDto.getOccupation();
        this.emergencyContactName = contactInfoDto.getEmergencyContactName();
        this.emergencyContactPhone = contactInfoDto.getEmergencyContactPhone();
        this.status = contactInfoDto.getStatus();
    }

}