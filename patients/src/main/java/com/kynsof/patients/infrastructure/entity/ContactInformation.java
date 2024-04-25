package com.kynsof.patients.infrastructure.entity;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ContactInformation {
    @Id
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
    @JoinColumn(name = "geographic_location_id",nullable = true)
    private GeographicLocation geographicLocation;

    public ContactInformation(ContactInfoDto contactInfoDto) {
        this.id = contactInfoDto.getId();
        this.patient = new Patients(contactInfoDto.getPatient());
        this.email = contactInfoDto.getEmail();
        this.telephone = contactInfoDto.getTelephone();
        this.address = contactInfoDto.getAddress();
        this.birthdayDate = contactInfoDto.getBirthdayDate();
        this.status = contactInfoDto.getStatus();
        this.geographicLocation =contactInfoDto.getGeographicLocation() != null ? new GeographicLocation(contactInfoDto.getGeographicLocation()) : null;
    }

    public ContactInfoDto toAggregate() {
        GeographicLocationDto geographicLocationDto = geographicLocation != null ? geographicLocation.toAggregate() : null;
        return new ContactInfoDto(getId(), getPatient().toAggregate(), getEmail(), getTelephone(), getAddress(), getBirthdayDate(), getStatus(),
                geographicLocationDto
                );
    }
}
