package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.DoctorDto;
import com.kynsof.treatments.domain.dto.enumDto.Status;
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
public class Doctor {
    @Id
    @Column(name="id")
    private UUID id;

    @Column(unique = true)
    private String identification;

    private String name;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Doctor(DoctorDto doctor) {
        this.id = doctor.getId();
        this.identification = doctor.getIdentification();
        this.name = doctor.getName();
        this.lastName = doctor.getLastName();
        this.status = doctor.getStatus();
    }

    public DoctorDto toAggregate() {
        return new DoctorDto(this.id, this.identification, this.name,this.lastName,this.status);
    }
}
