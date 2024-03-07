package com.kynsof.calendar.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kynsof.calendar.domain.dto.enumType.EResourceStatus;
import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.share.core.domain.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Resource extends BaseEntity {

    private String picture;

    @Size(max = 250)
    @NotBlank
    private String name;

    @Size(max = 10)
    @NotBlank
    private String registrationNumber;

    @Size(max = 100)
    private String language;

    @Enumerated(EnumType.STRING)
    private EResourceStatus status;

    private Boolean expressAppointments;

    @Column(nullable = true)
    private boolean deleted;

    @JsonIgnoreProperties("resources")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "resource_service",
            joinColumns = @JoinColumn(name = "resource_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<Services> services = new HashSet<>();

    @JsonIgnoreProperties({"qualifications", "resources"})
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "resource_qualification",
            joinColumns = @JoinColumn(name = "resource_id"),
            inverseJoinColumns = @JoinColumn(name = "qualification_id")
    )
    private Set<Qualification> qualifications = new HashSet<>();

    // Relación de muchos a muchos con Business
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "resource_business",
            joinColumns = @JoinColumn(name = "resource_id"),
            inverseJoinColumns = @JoinColumn(name = "business_id")
    )
    private Set<Business> businesses = new HashSet<>();

    public Resource(ResourceDto resourceDto) {
        this.id = resourceDto.getId();
        this.picture = resourceDto.getPicture();
        this.name = resourceDto.getName();
        this.registrationNumber = resourceDto.getRegistrationNumber();
        this.language = resourceDto.getLanguage();
        this.status = resourceDto.getStatus();
        this.expressAppointments = resourceDto.getExpressAppointments();
        this.createdAt = resourceDto.getCreateAt();
        this.updatedAt = resourceDto.getUpdateAt();
        this.deletedAt = resourceDto.getDeleteAt();
    }

    public ResourceDto toAggregate () {
        return new ResourceDto(id, picture, name, registrationNumber, language, status, expressAppointments, createdAt, updatedAt, deletedAt, deleted);
    }
}
