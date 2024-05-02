package com.kynsof.calendar.infrastructure.entity;

import com.kynsof.calendar.domain.dto.BusinessServicesDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "business_services")
@Getter
@Setter
@NoArgsConstructor
public class BusinessServices {
    @Id
    private UUID id;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    private Business business;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private Services services;

    private Double price;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public BusinessServices(BusinessServicesDto businessServices) {
        this.id = businessServices.getId();
        this.business = new Business(businessServices.getBusiness());
        this.services = new Services(businessServices.getService());
        this.price = businessServices.getPrice();
    }

    public BusinessServicesDto toAggregate () {
        return new BusinessServicesDto(id, business.toAggregate(), services.toAggregate(),price, createdAt);
    }

}
