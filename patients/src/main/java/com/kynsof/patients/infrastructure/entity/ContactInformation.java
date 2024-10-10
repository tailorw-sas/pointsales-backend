package com.kynsof.patients.infrastructure.entity;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ContactInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "patients_id", referencedColumnName = "id")
    private Patients patient;

    private String email;

    private String telephone;

    private String address;

    private LocalDate birthdayDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne()
    @JoinColumn(name = "parroquia_id", nullable = true)
    private GeographicLocation parroquia;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ContactInformation(ContactInfoDto contactInfoDto) {
        this.id = contactInfoDto.getId();
        this.patient = new Patients(contactInfoDto.getPatient());
        this.email = contactInfoDto.getEmail();
        this.telephone = contactInfoDto.getTelephone();
        this.address = contactInfoDto.getAddress();
        this.birthdayDate = contactInfoDto.getBirthdayDate();
        this.status = contactInfoDto.getStatus();
        this.parroquia = contactInfoDto.getParroquia() != null ? new GeographicLocation(contactInfoDto.getParroquia()) : null;
    }

    public ContactInfoDto toAggregate() {

        GeographicLocationDto parroquiaDto = parroquia != null ? parroquia.toAggregate() : null;
        return new ContactInfoDto(
                getId(), 
                getPatient().toAggregate(), 
                getEmail(), 
                getTelephone(), 
                getAddress(), 
                getBirthdayDate(), 
                getStatus(),
                parroquiaDto
        );
    }

    public ContactInfoDto toAggregateSimple() {

        GeographicLocationDto parroquiaDto = parroquia != null ? parroquia.toAggregate() : null;
        return new ContactInfoDto(
                getId(),
                null,
                getEmail(),
                getTelephone(),
                getAddress(),
                getBirthdayDate(),
                getStatus(),
                parroquiaDto
        );
    }
}
