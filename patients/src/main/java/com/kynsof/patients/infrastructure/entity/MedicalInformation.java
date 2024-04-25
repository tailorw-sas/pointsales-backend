package com.kynsof.patients.infrastructure.entity;

import com.kynsof.patients.domain.dto.AllergyDto;
import com.kynsof.patients.domain.dto.CurrentMedicationDto;
import com.kynsof.patients.domain.dto.MedicalInformationDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class MedicalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String bloodType;

    private String medicalHistory;

    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne
    @JoinColumn(name = "patients_id", referencedColumnName = "id")
    private Patients patient;

    @OneToMany(mappedBy = "medicalInformation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Allergy> allergies;

    @OneToMany(mappedBy = "medicalInformation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CurrentMedication> currentMedications;

    public MedicalInformation(MedicalInformationDto dto) {
        this.id = dto.getId();
        this.bloodType = dto.getBloodType();
        this.medicalHistory = dto.getMedicalHistory();
        this.patient = new Patients(dto.getPatientDto());
        this.status = dto.getStatus();
        this.allergies = dto.getAllergies().stream()
                .map(allergyDto -> {
                    Allergy allergy = new Allergy();
                    allergy.setCode(allergyDto.getCode());
                    allergy.setName(allergyDto.getName());
                    allergy.setMedicalInformation(this);
                    allergy.setStatus(allergyDto.getStatus());
                    return allergy;
                })
                .collect(Collectors.toList());
        this.currentMedications = dto.getCurrentMedications().stream()
                .map(medicationDto -> {
                    CurrentMedication medication = new CurrentMedication();
                    medication.setName(medicationDto.getName());
                    medication.setDosage(medicationDto.getDosage());
                    medication.setMedicalInformation(this);
                    medication.setStatus(medicationDto.getStatus());
                    return medication;
                })
                .collect(Collectors.toList());

    }

    public MedicalInformationDto toAggregate() {
        List<AllergyDto> allergyDtos = allergies.stream()
                .map(allergy -> {
                    AllergyDto dto = new AllergyDto();
                    dto.setId(allergy.getId());
                    dto.setCode(allergy.getCode());
                    dto.setName(allergy.getName());
                    dto.setStatus(allergy.getStatus());
                    return dto;
                })
                .collect(Collectors.toList());

        List<CurrentMedicationDto> currentMedicationDtos = currentMedications.stream()
                .map(medication -> {
                    CurrentMedicationDto dto = new CurrentMedicationDto();
                    dto.setId(medication.getId());
                    dto.setName(medication.getName());
                    dto.setDosage(medication.getDosage());
                    dto.setStatus(medication.getStatus());
                    return dto;
                })
                .collect(Collectors.toList());
        return new MedicalInformationDto(this.getId(), this.getBloodType(), this.getMedicalHistory(),
                patient.getId(), patient.toAggregate(), allergyDtos, currentMedicationDtos, this.getStatus());
    }
}
