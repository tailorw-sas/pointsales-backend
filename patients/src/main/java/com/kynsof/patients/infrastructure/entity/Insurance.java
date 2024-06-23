package com.kynsof.patients.infrastructure.entity;

import com.kynsof.patients.domain.dto.InsuranceDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
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

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Insurance(InsuranceDto insurance) {
        this.id = insurance.getId();
        this.name = insurance.getName();
        this.description = insurance.getDescription();
    }

    public InsuranceDto toAggregate() {
        return new InsuranceDto(this.id,this.name, this.description);
    }
}
