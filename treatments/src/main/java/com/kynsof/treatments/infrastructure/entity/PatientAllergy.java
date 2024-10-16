package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.PatientAllergyDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class PatientAllergy {

    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patients patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cie10_id", nullable = false)
    private Cie10 cie10;  // Asociación con el CIE10 para clasificar la alergia

    private String severity;  // La severidad de la alergia (leve, moderada, grave)
    private String reaction;  // La reacción que causa la alergia (urticaria, anafilaxia, etc.)

    public PatientAllergy(PatientAllergyDto dto) {
        this.id = dto.getId();
        this.severity = dto.getSeverity();
        this.reaction = dto.getReaction();
        this.patient = new Patients(dto.getPatient());
        this.cie10 = new Cie10(dto.getCie10());
    }

    public PatientAllergyDto toAggregate() {
        return new PatientAllergyDto(id, patient.toAggregate(), cie10.toAggregate(), severity, reaction);
    }
}