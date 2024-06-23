package com.kynsof.patients.infrastructure.entity;

import com.kynsof.patients.domain.dto.CurrentMerdicationEntityDto;
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
public class CurrentMedication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String dosage;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "medical_information_id", nullable = false)
    private MedicalInformation medicalInformation;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public CurrentMedication(CurrentMerdicationEntityDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.dosage = dto.getDosage();
        this.status = dto.getStatus();
        this.medicalInformation = new MedicalInformation(dto.getMedicalInformationDto());
    }

    public CurrentMerdicationEntityDto toAggregate() {
        return new CurrentMerdicationEntityDto(this.id, this.name, this.dosage, this.status,
                this.medicalInformation.getId(), this.medicalInformation.toAggregate());
    }
}
