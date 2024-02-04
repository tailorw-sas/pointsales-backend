package com.kynsof.patients.infrastructure.entity;

import com.kynsof.patients.domain.dto.AllergyEntityDto;
import com.kynsof.patients.domain.dto.EStatusPatients;
import jakarta.persistence.*;

import java.util.UUID;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Allergy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String code;

    private String name;
    @Enumerated(EnumType.STRING)
    private EStatusPatients status;

    @ManyToOne
    @JoinColumn(name = "medical_information_id", nullable = false)
    private MedicalInformation medicalInformation;

    public Allergy(AllergyEntityDto dto) {
        this.id = dto.getId();
        this.code = dto.getCode();
        this.name = dto.getName();
        this.status = dto.getStatus();
        this.medicalInformation = new MedicalInformation(dto.getMedicalInformationDto());
    }

    public AllergyEntityDto toAggregate() {
        return new AllergyEntityDto(this.id, this.getCode(), this.getName(), this.getStatus(),
                this.medicalInformation.getId(), this.getMedicalInformation().toAggregate());
    }
}
