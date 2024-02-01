package com.kynsof.patients.infrastructure.dao;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class InsuranceInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String insuranceCompany;

    private String policyNumber;

    @OneToOne
    @JoinColumn(name = "patients_id", referencedColumnName = "id")
    private PatientsDAO patient;
}
