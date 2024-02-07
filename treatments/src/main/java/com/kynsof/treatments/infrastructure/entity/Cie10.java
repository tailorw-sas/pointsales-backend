package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.Cie10Dto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Cie10 {
    @Id
    @Column(name="id")
    private UUID id;

    @Column(unique = true)
    private String code;

    private String name;

    public Cie10Dto toAggregate() {
        return new Cie10Dto(this.id,this.code, this.name);
    }
}