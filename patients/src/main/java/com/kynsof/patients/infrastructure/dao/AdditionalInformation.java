package com.kynsof.patients.infrastructure.dao;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class AdditionalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String maritalStatus;

    private String occupation;

    private String emergencyContactName;

    private String emergencyContactPhone;

    @OneToOne
    @JoinColumn(name = "patients_id", referencedColumnName = "id")
    private PatientsDAO patient;
}
