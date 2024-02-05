package com.kynsof.patients.infrastructure.entity;

import com.kynsof.patients.domain.dto.AdditionalInformationDto;
import com.kynsof.patients.domain.dto.Status;
import jakarta.persistence.*;

import java.util.UUID;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class AdditionalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String maritalStatus;

    private String occupation;

    private String emergencyContactName;

    private String emergencyContactPhone;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patients patient;

    public AdditionalInformation(AdditionalInformationDto additionalInformationDto) {
        id = additionalInformationDto.getId();
        maritalStatus = additionalInformationDto.getMaritalStatus();
        occupation = additionalInformationDto.getOccupation();
        emergencyContactName = additionalInformationDto.getEmergencyContactName();
        emergencyContactPhone = additionalInformationDto.getEmergencyContactPhone();
        patient = additionalInformationDto.getPatient();
        status = additionalInformationDto.getStatus();
    }

    public AdditionalInformationDto toAggregate() {
        return new AdditionalInformationDto(getId(), getPatient(), getMaritalStatus(), getOccupation(),
                getEmergencyContactName(), getEmergencyContactPhone(), getStatus());
    }
}
