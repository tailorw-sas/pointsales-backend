package com.kynsof.patients.infrastructure.dao;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class CurrentMedication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String dosage;

    @ManyToOne
    @JoinColumn(name = "medical_information_id")
    private MedicalInformation medicalInformation;
}
