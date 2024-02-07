package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.Cie10Dto;
import com.kynsof.treatments.domain.dto.ProcedureDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String code;
    private String name;
    private String description;
    private String type;
    private Double price;

    public ProcedureDto toAggregate() {
        return new ProcedureDto(this.id,this.code,this.name,this.description,this.type,this.price);
    }
}
