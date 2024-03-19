package com.kynsof.patients.infrastructure.entity;

import com.kynsof.patients.domain.dto.ContactInfoDto;
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
    @JoinColumn(name = "geographic_location_id")
    private GeographicLocation geographicLocation;

    public ContactInformation(ContactInfoDto contactInfoDto) {
        this.patient = new Patients(contactInfoDto.getPatient());
        this.email = contactInfoDto.getEmail();
        this.telephone = contactInfoDto.getTelephone();
        this.address = contactInfoDto.getAddress();
        this.birthdayDate = contactInfoDto.getBirthdayDate();
        this.status = contactInfoDto.getStatus();
        this.geographicLocation = new GeographicLocation(contactInfoDto.getGeographicLocation());
    }

    public ContactInfoDto toAggregate() {
        return new ContactInfoDto(getId(), getPatient().toAggregate(), getEmail(), getTelephone(), getAddress(), getBirthdayDate(), getStatus(),
                geographicLocation.toAggregate());
    }
}
