package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.PathologicalHistoryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class PathologicalHistory {

    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patients patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cie10_id", nullable = false)
    private Cie10 cie10;
    private String observations;

    private String status;
    private String type;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public PathologicalHistory(PathologicalHistoryDto dto) {
        this.id = dto.getId();
        this.observations = dto.getObservations();
        this.patient = new Patients(dto.getPatient());
        this.cie10 = new Cie10(dto.getCie10());
        this.status = dto.getStatus();
        this.type = dto.getType();
    }

    public PathologicalHistoryDto toAggregate() {
        PathologicalHistoryDto patientAllergyDto = new PathologicalHistoryDto(id, patient.toAggregate(),
                cie10.toAggregate(), observations,status,type);
        patientAllergyDto.setCreatedAt(createdAt);
        return patientAllergyDto;
    }
}