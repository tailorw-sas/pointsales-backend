package com.kynsof.scheduled.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kynsof.scheduled.domain.dto.QualificationDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Qualification {
    @Id
    private UUID id;

    @NotBlank
    @Column(unique = true)
    private String description;

    @JsonIgnoreProperties("specialists")
    @ManyToMany(mappedBy = "qualifications")
    private Set<Resource> specialists = new HashSet<>();

    public Qualification(UUID id, String description) {
        this.id = id;
        this.description = description;
    }

    public Qualification(QualificationDto qualification) {
        this.id = qualification.getId();
        this.description = qualification.getDescription();
    }

    public QualificationDto toAggregate () {
        return new QualificationDto(id, description);
    }
}