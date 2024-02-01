package com.kynsof.patients.infrastructure.entity;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Allergy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String code;

    private String name;

    @ManyToOne
    @JoinColumn(name = "medical_information_id")
    private MedicalInformation medicalInformation;
}
