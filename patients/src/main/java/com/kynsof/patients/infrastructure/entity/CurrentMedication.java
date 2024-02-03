package com.kynsof.patients.infrastructure.entity;

import com.kynsof.patients.domain.dto.EStatusPatients;
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

    @Enumerated(EnumType.STRING)
    private EStatusPatients status;

    @ManyToOne
    @JoinColumn(name = "medical_information_id", nullable = false)
    private MedicalInformation medicalInformation;
}
