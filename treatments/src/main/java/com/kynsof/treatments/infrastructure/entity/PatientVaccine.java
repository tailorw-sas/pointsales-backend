package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.PatientVaccineDto;
import com.kynsof.treatments.domain.dto.enumDto.VaccinationStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class PatientVaccine {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patient;

    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;

    @Enumerated(EnumType.STRING)
    private VaccinationStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date vaccinationDate;

    @CreationTimestamp
    @Column(nullable = true, updatable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true, updatable = true)
    private LocalDateTime updatedAt;

    public PatientVaccine(PatientVaccineDto dto) {
        this.id = dto.getId();
        this.patient = new Patients(dto.getPatient());
        this.vaccine = new Vaccine(dto.getVaccine());
        this.status = dto.getStatus();
    }

    @PrePersist
    protected void onCreate() {
        vaccinationDate = new Date();
    }

    public PatientVaccineDto toAggregate() {
        return new PatientVaccineDto(this.id, this.patient.toAggregate(), this.vaccine.toAggregate(), this.status, this.vaccinationDate);
    }
}
