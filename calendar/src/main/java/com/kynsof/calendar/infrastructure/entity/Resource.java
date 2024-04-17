package com.kynsof.calendar.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.enumType.EResourceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private String identification;

    @OneToMany(mappedBy = "resource", fetch = FetchType.EAGER)
    private Set<ResourceService> resourceServices = new HashSet<>();

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

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BusinessResource> businessResources = new HashSet<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Resource(ResourceDto resourceDto) {
        this.id = resourceDto.getId();
        this.name = resourceDto.getName();
        this.registrationNumber = resourceDto.getRegistrationNumber();
        this.language = resourceDto.getLanguage();
        this.status = resourceDto.getStatus();
        this.expressAppointments = resourceDto.getExpressAppointments();
        this.image = resourceDto.getImage();
        this.identification = resourceDto.getIdentification() != null ? resourceDto.getIdentification() : null;
        //services = resourceDto.getServices() != null ? resourceDto.getServices().stream().map(Services::new).collect(Collectors.toSet()) : null;
    }

    public ResourceDto toAggregate() {
        // List<ServiceDto> serviceDtos = services.stream().map(Services::toAggregate).toList();
        return new ResourceDto(id, name, registrationNumber, language, status, expressAppointments, image, identification, new ArrayList<>());
    }
}
