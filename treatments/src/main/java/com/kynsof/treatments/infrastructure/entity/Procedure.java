package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.ProcedureDto;
import com.kynsof.treatments.domain.dto.enumDto.MedicalExamCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
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

    @OneToMany(mappedBy = "procedure", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BusinessProcedure> businessProcedures = new HashSet<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Procedure(ProcedureDto procedureDto) {
        this.id = procedureDto.getId();
        this.code = procedureDto.getCode();
        this.name = procedureDto.getName();
        this.description = procedureDto.getDescription();
        this.type = procedureDto.getType();

    }

    public ProcedureDto toAggregate() {
        return new ProcedureDto(this.id,this.code,this.name,this.description,this.type);
    }
}
