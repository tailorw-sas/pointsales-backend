package com.kynsof.calendar.infrastructure.entity;

import com.kynsof.calendar.domain.dto.PatientDto;
import com.kynsof.calendar.domain.dto.enumType.PatientStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Patient {
    @Id
    @Column(name="id")
    private UUID id;

    private String identification;

    private String name;

    private String lastName;

    private String gender;


    private UUID logo;

    @Enumerated(EnumType.STRING)
    private PatientStatus status;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Patient(PatientDto patients) {
        this.id = patients.getId();
        this.identification = patients.getIdentification();
        this.name = patients.getName();
        this.lastName = patients.getLastName();
        this.gender = patients.getGender();
        this.status = patients.getStatus();
        this.logo = patients.getLogo();
    }

    public PatientDto toAggregate() {
        return new PatientDto(id, identification, name, lastName, gender, status, logo);
    }
}
