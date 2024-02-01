package com.kynsof.scheduled.infrastructure.entity;

import com.fasterxml.jackson.annotation.*;
import com.kynsof.scheduled.domain.dto.EServiceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    private EServiceType type;

    private Boolean expressAppointments;

    @JsonIgnoreProperties("specialists")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "specialist_service",
            joinColumns = @JoinColumn(name = "specialist_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<Service> services = new HashSet<>();

    @JsonIgnoreProperties({"qualifications", "specialists"})
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "specialist_qualification",
            joinColumns = @JoinColumn(name = "specialist_id"),
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

    // Resto del código...
}
