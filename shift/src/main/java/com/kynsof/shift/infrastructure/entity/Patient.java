package com.kynsof.shift.infrastructure.entity;

import com.kynsof.shift.domain.dto.PatientDto;
import com.kynsof.shift.domain.dto.enumType.PatientStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Patient {

    @Id
    @Column(name = "id")
    private UUID id;

    private String identification;

    private String email;

    private String name;

    private String lastName;

    private String image;

    @Enumerated(EnumType.STRING)
    private PatientStatus status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Patient(PatientDto patients) {
        this.id = patients.getId();
        this.identification = patients.getIdentification();
        this.email = patients.getEmail();
        this.name = patients.getName();
        this.lastName = patients.getLastName();
        this.status = patients.getStatus();
        this.image = patients.getImage();
    }

    public PatientDto toAggregate() {
        return new PatientDto(id, identification, email, name, lastName, status, image);
    }
}
