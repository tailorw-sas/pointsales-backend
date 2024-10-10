package com.kynsof.patients.infrastructure.entity;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.DependentPatientDto;
import com.kynsof.patients.domain.dto.PatientByIdDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.enumTye.DisabilityType;
import com.kynsof.patients.domain.dto.enumTye.FamilyRelationship;
import com.kynsof.patients.domain.dto.enumTye.GenderType;
import com.kynsof.patients.domain.dto.enumTye.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Patients implements Serializable {
    @Id
    @Column(name = "id")
    private UUID id;

    private String identification;

    private String firstName;

    private String lastName;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private FamilyRelationship familyRelationship;
    private String photo;
    private Double weight;
    private Double height;
    private Boolean hasDisability;
    @Enumerated(EnumType.STRING)
    private DisabilityType disabilityType;
    private Boolean isPregnant;
    private int gestationTime = 0;

    @OneToOne(mappedBy = "patient", orphanRemoval = true)
    private ContactInformation contactInformation;

    @OneToOne(mappedBy = "patient", orphanRemoval = true)
    private MedicalInformation medicalInformation;

    @OneToOne(mappedBy = "patient", orphanRemoval = true)
    private AdditionalInformation additionalInformation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prime_id")
    private Patients prime;

    @OneToMany(mappedBy = "prime", fetch = FetchType.LAZY)
    private List<Patients> dependents = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "patient_insurance", joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "insurance_id")
    )
    private List<Insurance> insurances;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Patients(PatientDto patients) {
        this.id = patients.getId();
        this.identification = patients.getIdentification();
        this.firstName = patients.getName();
        this.lastName = patients.getLastName();
        this.gender = patients.getGender() != null ? patients.getGender() : GenderType.UNDEFINED ;
        this.status = patients.getStatus();
        this.weight = patients.getWeight();
        this.height = patients.getHeight();
        this.hasDisability = patients.getHasDisability();
        this.isPregnant = patients.getIsPregnant();
        this.photo = patients.getPhoto();
        this.disabilityType = patients.getDisabilityType() != null ? patients.getDisabilityType() : DisabilityType.UNDEFINED;
        this.gestationTime = patients.getGestationTime();
    }

    public Patients(DependentPatientDto patients) {
        this.id = patients.getId();
        this.identification = patients.getIdentification();
        this.firstName = patients.getName();
        this.lastName = patients.getLastName();
        this.gender = patients.getGender() != null ? patients.getGender() : GenderType.OTHER;
        this.status = patients.getStatus();
        this.setPrime(new Patients(patients.getPrime()));
        this.weight = patients.getWeight();
        this.height = patients.getHeight();
        this.hasDisability = patients.getHasDisability();
        this.isPregnant = patients.getIsPregnant();
        this.photo = patients.getPhoto();
        this.disabilityType = patients.getDisabilityType() != null ? patients.getDisabilityType() : DisabilityType.UNDEFINED;
        this.gestationTime = patients.getGestationTime();
        this.familyRelationship = patients.getFamilyRelationship() != null ? patients.getFamilyRelationship() : FamilyRelationship.UNDEFINED;
    }

    public PatientDto toAggregate() {
        PatientDto patientDto = new PatientDto(id, identification, firstName, lastName, gender, status, weight, height, hasDisability, isPregnant,
                photo, disabilityType, gestationTime);
        ContactInfoDto contactInfoDto = contactInformation != null ? contactInformation.toAggregateSimple() : null;
        patientDto.setContactInfo(contactInfoDto);
        return patientDto;
    }

    public PatientByIdDto toAggregateById() {
        ContactInfoDto contactInfoDto = contactInformation != null ? contactInformation.toAggregateSimple() : null;
        PatientByIdDto result = new PatientByIdDto(id, identification, firstName, lastName, gender, status, weight, height,
                hasDisability, isPregnant, photo, disabilityType, gestationTime, familyRelationship,
                contactInfoDto );
        result.setContactInfoDto(contactInfoDto);
        return result;

    }
}
