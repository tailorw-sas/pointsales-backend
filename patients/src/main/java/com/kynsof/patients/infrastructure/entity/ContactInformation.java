package com.kynsof.patients.infrastructure.entity;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ContactInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; // Cambiado para evitar conflictos con el ID del paciente

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    private Patients patient;

    private String email;

    private String telephone;

    private String address;

    private LocalDate birthdayDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    public ContactInformation(ContactInfoDto contactInfoDto) {
        this.patient = contactInfoDto.getPatient();
        this.email = contactInfoDto.getEmail();
        this.telephone = contactInfoDto.getTelephone();
        this.address = contactInfoDto.getAddress();
        this.birthdayDate = contactInfoDto.getBirthdayDate();
        this.status = contactInfoDto.getStatus();
    }

    public ContactInfoDto toAggregate() {
        return new ContactInfoDto(getId(), getPatient(), getEmail(), getTelephone(), getAddress(), getBirthdayDate(), getStatus());
    }
}
