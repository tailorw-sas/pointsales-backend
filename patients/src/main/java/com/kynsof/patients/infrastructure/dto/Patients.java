package com.kynsof.patients.infrastructure.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class Patients {
    @Id
    @Column(name="patients_id")
    private UUID id;

    
}
