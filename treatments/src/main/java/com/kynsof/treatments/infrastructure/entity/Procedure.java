package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.ProcedureDto;
import com.kynsof.treatments.domain.dto.enumDto.MedicalExamCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Procedure {
    @Id
    private UUID id;
    private String code;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private MedicalExamCategory type;
    private Double price;

    public Procedure(ProcedureDto procedureDto) {
        this.id = procedureDto.getId();
        this.code = procedureDto.getCode();
        this.name = procedureDto.getName();
        this.description = procedureDto.getDescription();
        this.type = procedureDto.getType();
        this.price = procedureDto.getPrice();
    }

    public ProcedureDto toAggregate() {
        return new ProcedureDto(this.id,this.code,this.name,this.description,this.type,this.price);
    }
}
