package com.kynsof.scheduled.infrastructure.entity;

import com.fasterxml.jackson.annotation.*;
import com.kynsof.scheduled.domain.dto.EResourceStatus;
import com.kynsof.scheduled.domain.dto.ResourceDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Resource {

    @Id
    private UUID id;

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
    
    @Column(nullable = true)
    private LocalDateTime createAt;

    @Column(nullable = true)
    private LocalDateTime updateAt;

    @Column(nullable = true)
    private LocalDateTime deleteAt;

    // Resto del código...

    public Resource(ResourceDto resourceDto) {
        this.id = resourceDto.getId();
        this.picture = resourceDto.getPicture();
        this.name = resourceDto.getName();
        this.registrationNumber = resourceDto.getRegistrationNumber();
        this.language = resourceDto.getLanguage();
        this.status = resourceDto.getStatus();
        this.expressAppointments = resourceDto.getExpressAppointments();
        this.createAt = resourceDto.getCreateAt();
        this.updateAt = resourceDto.getUpdateAt();
        this.deleteAt = resourceDto.getDeleteAt();
    }

    public ResourceDto toAggregate () {
        return new ResourceDto(id, picture, name, registrationNumber, language, status, expressAppointments, createAt, updateAt, deleteAt);
    }
}
