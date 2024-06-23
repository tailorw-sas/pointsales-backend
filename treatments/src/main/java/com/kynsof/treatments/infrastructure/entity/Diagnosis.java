package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.DiagnosisDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Diagnosis {

    @Id
    private UUID id;
    private String icdCode; // Código CIE-10
    private String description;

    @ManyToOne
    @JoinColumn(name = "external_consultation_id")
    private ExternalConsultation externalConsultation;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Diagnosis(DiagnosisDto diagnosisDto) {
        this.id = diagnosisDto.getId();
        this.icdCode = diagnosisDto.getIcdCode();
        this.description = diagnosisDto.getDescription();
        this.externalConsultation = diagnosisDto.getExternalConsultation() != null ? new ExternalConsultation(diagnosisDto.getExternalConsultation()) : null;
    }

    public DiagnosisDto toAggregate() {
        DiagnosisDto response = new DiagnosisDto(this.id, this.icdCode, this.description);
        response.setExternalConsultation(externalConsultation.toAggregate());
        return response;
    }

}
