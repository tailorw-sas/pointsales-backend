package com.kynsof.patients.infrastructure.entity;

import com.kynsof.patients.domain.dto.InsuranceDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Insurance {
    @Id
    private UUID id;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "insurances")
    private List<Patients> patients;

    public Insurance(InsuranceDto insurance) {
        this.id = insurance.getId();
        this.name = insurance.getName();
        this.description = insurance.getDescription();
    }

    public InsuranceDto toAggregate() {
        return new InsuranceDto(this.id,this.name, this.description);
    }
}
