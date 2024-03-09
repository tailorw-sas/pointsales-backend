package com.kynsof.patients.infrastructure.entity;

import com.kynsof.patients.domain.dto.DependentPatientDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.enumTye.FamilyRelationship;
import com.kynsof.patients.domain.dto.enumTye.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
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
    @Column(name="id")
    private UUID id;

    @Column(unique = true, nullable = true)
    private String identification;

    private String name;

    private String lastName;

    private String gender;

    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private FamilyRelationship familyRelationship;
    private String photo;
    private Double weight; // Peso
    private Double height; // Talla
    private Boolean hasDisability; // Tiene discapacidad
    private Boolean isPregnant; // Está embarazada

    @OneToMany(mappedBy = "patient", orphanRemoval = true)
    private List<ContactInformation> contactInformation;

    @OneToOne(mappedBy = "patient",  orphanRemoval = true)
    private MedicalInformation medicalInformation;

    @OneToOne(mappedBy = "patient",  orphanRemoval = true)
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

    public Patients(PatientDto patients) {
        this.id = patients.getId();
        this.identification = patients.getIdentification();
        this.name = patients.getName();
        this.lastName = patients.getLastName();
        this.gender = patients.getGender();
        this.status = patients.getStatus();
        this.weight = patients.getWeight();
        this.height = patients.getHeight();
        this.hasDisability = patients.getHasDisability();
        this.isPregnant = patients.getIsPregnant();
        this.photo = patients.getPhoto();
    }

    public Patients(DependentPatientDto patients) {
        this.id = patients.getId();
        this.identification = patients.getIdentification();
        this.name = patients.getName();
        this.lastName = patients.getLastName();
        this.gender = patients.getGender();
        this.status = patients.getStatus();
        this.setPrime(new Patients(patients.getPrime()));
        this.weight = patients.getWeight();
        this.height = patients.getHeight();
        this.hasDisability = patients.getHasDisability();
        this.isPregnant = patients.getIsPregnant();
        this.photo = patients.getPhoto();

    }

    public PatientDto toAggregate() {
        return new PatientDto(id, identification, name, lastName, gender, status, weight, height, hasDisability, isPregnant,
                photo);
    }
}
