package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.MedicinesDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Medicines {

    @Id
    private UUID id;
    @Column(unique = true)
    private String name;

    @Column(nullable = true)
    private Boolean deleted = false;

    public Medicines(MedicinesDto medicine) {
        this.id = medicine.getId();
        this.name = medicine.getName();
    }

    public MedicinesDto toAggregate() {
        return new MedicinesDto(this.id, this.name);
    }

}
