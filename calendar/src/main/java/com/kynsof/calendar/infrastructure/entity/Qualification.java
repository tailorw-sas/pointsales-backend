package com.kynsof.calendar.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kynsof.calendar.domain.dto.EQualificationStatus;
import com.kynsof.calendar.domain.dto.QualificationDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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

    @Enumerated(EnumType.STRING)
    private EQualificationStatus status;

    @JsonIgnoreProperties("resources")
    @ManyToMany(mappedBy = "qualifications")
    private Set<Resource> resources = new HashSet<>();

    @Column(nullable = true)
    private LocalDateTime createAt;

    @Column(nullable = true)
    private LocalDateTime updateAt;

    @Column(nullable = true)
    private LocalDateTime deleteAt;

    public Qualification(UUID id, String description) {
        this.id = id;
        this.description = description;
    }

    public Qualification(QualificationDto qualification) {
        this.id = qualification.getId();
        this.description = qualification.getDescription();
        this.createAt = qualification.getCreateAt();
        this.deleteAt = qualification.getDeleteAt();
        this.updateAt = qualification.getUpdateAt();
        this.status = qualification.getStatus();
    }

    public QualificationDto toAggregate () {
        return new QualificationDto(id, description, status, createAt, updateAt, deleteAt);
    }
}