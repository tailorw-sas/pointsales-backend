package com.kynsof.patients.infrastructure.entity;

import com.kynsof.patients.domain.dto.AdditionalInformationDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

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

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    private LocalDateTime updatedAt;

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
