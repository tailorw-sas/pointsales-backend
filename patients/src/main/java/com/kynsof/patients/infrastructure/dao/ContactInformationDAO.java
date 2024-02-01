package com.kynsof.patients.infrastructure.dao;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ContactInformationDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; // Cambiado para evitar conflictos con el ID del paciente

    @ManyToOne
    @JoinColumn(name="patientId", nullable = false)
    private PatientsDAO patient;

    private String email;

    private String telephone;

    private String address;

    private LocalDate birthdayDate;
}
