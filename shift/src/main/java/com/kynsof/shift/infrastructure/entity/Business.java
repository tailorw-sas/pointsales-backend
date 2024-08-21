package com.kynsof.shift.infrastructure.entity;

import com.kynsof.shift.domain.dto.BusinessDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Business{
    @Id
    @Column(name="id")
    private UUID id;
    private String name;
    private String latitude;
    private String longitude;
    private String address;
    private String logo;
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BusinessResource> businessResources = new HashSet<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Business(BusinessDto business) {
        this.id = business.getId();
        this.name = business.getName();
        this.latitude = business.getLatitude();
        this.longitude = business.getLongitude();
        this.logo = business.getLogo();
        this.address = business.getAddress();

    }

    public BusinessDto toAggregate () {
        return new BusinessDto(id, name, latitude, longitude, address, logo);
    }
}
