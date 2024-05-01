package com.kynsof.calendar.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kynsof.calendar.domain.dto.QualificationDto;
import com.kynsof.calendar.domain.dto.enumType.EQualificationStatus;
import jakarta.persistence.*;
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
    protected UUID id;
    @NotBlank
    @Column(unique = true)
    private String description;

    @Column(nullable = true)
    private boolean deleted;

    @Enumerated(EnumType.STRING)
    private EQualificationStatus status;

    @JsonIgnoreProperties("resources")
    @ManyToMany(mappedBy = "qualifications")
    private Set<Resource> resources = new HashSet<>();

    public Qualification(UUID id, String description) {
        this.id = id;
        this.description = description;
    }

    public Qualification(QualificationDto qualification) {
        this.id = qualification.getId();
        this.description = qualification.getDescription();
        this.status = qualification.getStatus();
       this.deleted = qualification.isDeleted();
    }

    public QualificationDto toAggregate () {
        return new QualificationDto(id, description, status,  deleted);
    }
}