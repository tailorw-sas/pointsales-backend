package com.kynsof.patients.infrastructure.entity;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String insuranceCompany;

    private String policyNumber;

    @ManyToMany(mappedBy = "insurances")
    private List<Patients> patients;
}
