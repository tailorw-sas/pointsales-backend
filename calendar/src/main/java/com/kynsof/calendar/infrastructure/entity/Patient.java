package com.kynsof.calendar.infrastructure.entity;

import com.kynsof.calendar.domain.dto.PatientDto;
import com.kynsof.calendar.domain.dto.enumType.PatientStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(nullable = true, unique = false)
    private String gender;

    @Enumerated(EnumType.STRING)
    private PatientStatus status;

    public Patient(PatientDto patients) {
        this.id = patients.getId();
        this.identification = patients.getIdentification();
        this.name = patients.getName();
        this.lastName = patients.getLastName();
        this.gender = patients.getGender();
        this.status = patients.getStatus();
    }

    public PatientDto toAggregate() {
        return new PatientDto(id, identification, name, lastName, gender, status);
    }
}
