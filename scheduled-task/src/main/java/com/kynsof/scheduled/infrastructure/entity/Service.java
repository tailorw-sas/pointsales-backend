package com.kynsof.scheduled.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kynsof.scheduled.domain.dto.EServiceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private EServiceType type;

    private String picture;
    private String name;
    private Double normalAppointmentPrice;
    private Double expressAppointmentPrice;

    @Size(max = 2000)
    private String description;

    @JsonIgnoreProperties("services")
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "services")
    private Set<Resource> specialists = new HashSet<>();

    // Relación de muchos a muchos con Business
    @JsonIgnoreProperties("services")
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "service_business",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "business_id")
    )
    private Set<Business> businesses = new HashSet<>();

    // Resto del código...
}
