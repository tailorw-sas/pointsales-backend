package com.kynsof.shift.infrastructure.entity;

import com.kynsof.shift.domain.dto.BusinessResourceDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
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