package com.kynsof.patients.infrastructure.entity;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

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
    @JoinColumn(name = "province_id", nullable = true)
    private GeographicLocation province;

    @ManyToOne()
    @JoinColumn(name = "canton_id", nullable = true)
    private GeographicLocation canton;

    @ManyToOne()
    @JoinColumn(name = "parroquia_id", nullable = true)
    private GeographicLocation parroquia;

    @CreationTimestamp
    @Column(nullable = true, updatable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true, updatable = true)
    private LocalDateTime updatedAt;

    public ContactInformation(ContactInfoDto contactInfoDto) {
        this.id = contactInfoDto.getId();
        this.patient = new Patients(contactInfoDto.getPatient());
        this.email = contactInfoDto.getEmail();
        this.telephone = contactInfoDto.getTelephone();
        this.address = contactInfoDto.getAddress();
        this.birthdayDate = contactInfoDto.getBirthdayDate();
        this.status = contactInfoDto.getStatus();
        this.province = contactInfoDto.getProvince() != null ? new GeographicLocation(contactInfoDto.getProvince()) : null;
        this.canton = contactInfoDto.getCanton() != null ? new GeographicLocation(contactInfoDto.getCanton()) : null;
        this.parroquia = contactInfoDto.getParroquia() != null ? new GeographicLocation(contactInfoDto.getParroquia()) : null;
    }

    public ContactInfoDto toAggregate() {
        GeographicLocationDto provinceDto = province != null ? province.toAggregate() : null;
        GeographicLocationDto cantonDto = canton != null ? canton.toAggregate() : null;
        GeographicLocationDto parroquiaDto = parroquia != null ? parroquia.toAggregate() : null;
        return new ContactInfoDto(
                getId(), 
                getPatient().toAggregate(), 
                getEmail(), 
                getTelephone(), 
                getAddress(), 
                getBirthdayDate(), 
                getStatus(),
                provinceDto, 
                cantonDto, 
                parroquiaDto
        );
    }
}
