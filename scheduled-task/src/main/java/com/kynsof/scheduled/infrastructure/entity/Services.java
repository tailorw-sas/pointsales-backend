package com.kynsof.scheduled.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kynsof.scheduled.domain.dto.EServiceStatus;
import com.kynsof.scheduled.domain.dto.EServiceType;
import com.kynsof.scheduled.domain.dto.ServiceDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Services {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private EServiceType type;

    @Enumerated(EnumType.STRING)
    private EServiceStatus status;

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
    
    @Column(nullable = true)
    private LocalDateTime createAt;

    @Column(nullable = true)
    private LocalDateTime updateAt;

    @Column(nullable = true)
    private LocalDateTime deleteAt;

    public Services(ServiceDto object) {
        this.id = object.getId();
        this.type = object.getType();
        this.status = object.getStatus();
        this.picture = object.getPicture();
        this.name = object.getName();
        this.normalAppointmentPrice = object.getNormalAppointmentPrice();
        this.expressAppointmentPrice = object.getExpressAppointmentPrice();
        this.description = object.getDescription();
        this.createAt = object.getCreateAt();
        this.updateAt = object.getUpdateAt();
        this.deleteAt = object.getDeleteAt();
    }

    public ServiceDto toAggregate () {
        return new ServiceDto(id, type, status, picture, name, normalAppointmentPrice, expressAppointmentPrice, description, createAt, updateAt, deleteAt);
    }
}
