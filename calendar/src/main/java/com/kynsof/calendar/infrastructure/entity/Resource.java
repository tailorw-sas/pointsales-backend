package com.kynsof.calendar.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.enumType.EResourceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Resource {
    @Id
    protected UUID id;
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

    private UUID image;

    @JsonIgnoreProperties("resources")
    @ManyToMany(fetch = FetchType.EAGER, cascade = {
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
    @ManyToMany(fetch = FetchType.EAGER, cascade = {
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
    @ManyToMany(fetch = FetchType.EAGER, cascade = {
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
        this.name = resourceDto.getName();
        this.registrationNumber = resourceDto.getRegistrationNumber();
        this.language = resourceDto.getLanguage();
        this.status = resourceDto.getStatus();
        this.expressAppointments = resourceDto.getExpressAppointments();
        this.image = resourceDto.getImage();
        services = resourceDto.getServices().stream().map(Services::new).collect(Collectors.toSet());
    }

    public ResourceDto toAggregate () {
        List<ServiceDto> serviceDtos = services.stream().map(Services::toAggregate).toList();
        return new ResourceDto(id,  name, registrationNumber, language, status, expressAppointments, image, serviceDtos);
    }
}
