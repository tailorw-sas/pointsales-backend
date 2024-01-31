package com.kynsof.patients.infrastructure.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class PatientsDAO {
    @Id
    @Column(name="patients_id")
    private UUID id;

    private String identification;

    private String name;

    private String lastName;

    private String gender;
}
