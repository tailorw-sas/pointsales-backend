package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.VaccineDto;
import com.kynsof.treatments.domain.dto.enumDto.RouteOfAdministration;
import com.kynsof.treatments.domain.dto.enumDto.VaccineType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Vaccine {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private VaccineType type;
    private double minAge;
    private double maxAge;
    private String dose;
    @Enumerated(EnumType.STRING)
    private RouteOfAdministration routeOfAdministration;
    private String preventableDiseases;
    private UUID serviceId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Vaccine(VaccineDto vaccine) {
        this.id = vaccine.getId();
        this.name = vaccine.getName();
        this.description = vaccine.getDescription();
        this.type = vaccine.getType();
        this.minAge = vaccine.getMinAge();
        this.maxAge = vaccine.getMaxAge();
        this.dose = vaccine.getDose();
        this.routeOfAdministration = vaccine.getRouteOfAdministration();
        this.preventableDiseases = vaccine.getPreventableDiseases();
        this.serviceId = vaccine.getServiceId();
    }

    public VaccineDto toAggregate() {
        return new VaccineDto(this.id, this.name, this.description, this.type, this.minAge,this.maxAge,
                this.dose,this.routeOfAdministration, this.preventableDiseases, this.serviceId);
    }
}
