package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.TreatmentDto;
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
public class Treatment {

    @Id
    private UUID id;

    private String description;
    private String medication;
    private String dose;
    private String frequency;
    private String duration;

    @ManyToOne
    @JoinColumn(name = "external_consultation_id")
    private ExternalConsultation externalConsultation;

    public Treatment(TreatmentDto treatmentDto) {
        this.id = treatmentDto.getId();
        this.description = treatmentDto.getDescription();
        this.medication = treatmentDto.getMedication();
        this.dose = treatmentDto.getDose();
        this.frequency = treatmentDto.getFrequency();
        this.duration = treatmentDto.getDuration();
        this.externalConsultation = treatmentDto.getExternalConsultationDto() != null ? new ExternalConsultation(treatmentDto.getExternalConsultationDto()) : null;
    }

    public TreatmentDto toAggregate() {
        return new TreatmentDto(this.id, this.medication, this.description, this.dose, this.frequency, this.duration);
    }
}
