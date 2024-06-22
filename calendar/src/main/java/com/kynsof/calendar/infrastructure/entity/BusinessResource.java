package com.kynsof.calendar.infrastructure.entity;

import com.kynsof.calendar.domain.dto.BusinessResourceDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "business_resource")
@Getter
@Setter
@NoArgsConstructor
public class BusinessResource {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_id")
    private Business business;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @Column(name = "createdAt", nullable = true, updatable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true, updatable = true)
    private LocalDateTime updatedAt;

    public BusinessResource(BusinessResourceDto businessResourceDto) {
        this.id = businessResourceDto.getId();
        this.business = new Business(businessResourceDto.getBusiness());
        this.resource = new Resource(businessResourceDto.getResource());
        this.createdAt = businessResourceDto.getCreatedAt();
    }

    public BusinessResourceDto toAggregate () {
        return new BusinessResourceDto(id, business.toAggregate(), resource.toAggregate(), createdAt);
    }
}