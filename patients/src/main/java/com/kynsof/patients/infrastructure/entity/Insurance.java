package com.kynsof.patients.infrastructure.entity;

import com.kynsof.patients.domain.dto.InsuranceDto;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "insurances")
    private List<Patients> patients;

    public InsuranceDto toAggregate() {
        return new InsuranceDto(this.id,this.name, this.description);
    }
}
