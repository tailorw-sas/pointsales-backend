package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.BusinessDto;
import com.kynsof.identity.domain.dto.enumType.EBusinessStatus;
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
public class Business {
    @Id
    @Column(name = "id")
    protected UUID id;
    private String name;
    private String latitude;
    private String longitude;
    private String description;
    private String logo;
    private String ruc;
    @Column()
    private String address;
    @Enumerated(EnumType.STRING)
    private EBusinessStatus status;
    private double balance;
    @OneToMany(mappedBy = "business")
    private Set<UserPermissionBusiness> userPermissionBusinesses = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "geographicLocation_id")
    private GeographicLocation geographicLocation;

    @OneToMany(mappedBy = "business")
    private Set<Session> sessions = new HashSet<>();
    @Column(name = "allowed_session_count")
    private Integer allowedSessionCount;

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
        this.description = business.getDescription();
        this.logo = business.getLogo();
        this.ruc = business.getRuc();
        this.status = business.getStatus();
        this.geographicLocation = business.getGeographicLocationDto() != null ? new GeographicLocation(business.getGeographicLocationDto()) : null;
        this.address = business.getAddress();
        this.balance = business.getBalance();
        this.allowedSessionCount = business.getAllowedSessionCount();
    }

    public BusinessDto toAggregate () {
        BusinessDto businessDto = new BusinessDto(
                id, 
                name, 
                latitude, 
                longitude, 
                description, 
                logo,
                ruc, 
                status, 
                geographicLocation != null ? geographicLocation.toAggregate() : null,
                address,
                allowedSessionCount
        );
        businessDto.setBalance(balance);
        businessDto.setCreateAt(createdAt);
        return businessDto;
    }
}
