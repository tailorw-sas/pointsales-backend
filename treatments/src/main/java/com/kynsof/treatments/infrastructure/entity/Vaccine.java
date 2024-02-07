package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.VaccineDto;
import com.kynsof.treatments.domain.enumDto.VaccineType;
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
public class Vaccine {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private VaccineType type;
    private Integer minAge;
    private Integer maxAge;

    public Vaccine(VaccineDto vaccine) {
        this.id = vaccine.getId();
        this.name = vaccine.getName();
        this.description = vaccine.getDescription();
        this.type = vaccine.getType();
        this.minAge = vaccine.getMinAge();
        this.maxAge = vaccine.getMaxAge();
    }

    public VaccineDto toAggregate() {
        return new VaccineDto(this.id, this.name, this.description, this.type, this.minAge,this.maxAge);
    }
}
