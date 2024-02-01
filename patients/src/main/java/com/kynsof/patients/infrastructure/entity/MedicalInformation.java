package com.kynsof.patients.infrastructure.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class MedicalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String bloodType;

    @Lob
    private String medicalHistory;

    @OneToOne
    @JoinColumn(name = "patients_id", referencedColumnName = "id")
    private PatientsDAO patient;

    @OneToMany(mappedBy = "medicalInformation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Allergy> allergies;

    @OneToMany(mappedBy = "medicalInformation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CurrentMedication> currentMedications;
}
