package com.kynsof.calendar.infrastructure.entity;

import com.kynsof.calendar.domain.dto.TurnerSpecialtiesDto;
import com.kynsof.calendar.domain.dto.enumType.ETurnerSpecialtiesStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class TurnerSpecialties {

    @Id
    protected UUID id;

    private LocalDateTime shiftDateTime;
    private LocalTime consultationTime;

    private String medicalHistory;
    private String patient;
    private String identification;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private Services service;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @Enumerated(EnumType.STRING)
    private ETurnerSpecialtiesStatus status;

    @Column(updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @PrePersist
    protected void prePersist() {
        this.createdAt = OffsetDateTime.now(ZoneId.of("UTC"));
    }

    public TurnerSpecialties(TurnerSpecialtiesDto dto) {
        this.id = dto.getId();
        this.medicalHistory = dto.getMedicalHistory();
        this.patient = dto.getPatient();
        this.identification = dto.getIdentification();
        this.status = dto.getStatus();
        this.resource = dto.getResource() != null ? new Resource(dto.getResource()) : null;
        this.service = dto.getService() != null ? new Services(dto.getService()) : null;
    }

    public TurnerSpecialtiesDto toAggregate() {
        return new TurnerSpecialtiesDto(
                id,
                medicalHistory,
                patient,
                identification,
                resource != null ? resource.toAggregate() : null,
                service != null ? service.toAggregate() : null,
                status,
                shiftDateTime,
                consultationTime,
                business.toAggregate()
        );
    }
}
