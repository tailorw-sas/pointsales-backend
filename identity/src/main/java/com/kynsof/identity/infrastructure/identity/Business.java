package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.BusinessDto;
import com.kynsof.identity.domain.dto.enumType.EBusinessStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Enumerated(EnumType.STRING)
    private EBusinessStatus status;
    private boolean deleted;

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRoleBusiness> userRoleBusinesses = new HashSet<>();

    public Business(BusinessDto business) {
        this.id = business.getId();
        this.name = business.getName();
        this.latitude = business.getLatitude();
        this.longitude = business.getLongitude();
        this.description = business.getDescription();
        this.logo = business.getLogo();
        this.ruc = business.getRuc();
        this.status = business.getStatus();
        this.deleted = business.isDeleted();
    }

    public BusinessDto toAggregate () {
        return new BusinessDto(id, name, latitude, longitude, description, logo,
                ruc, status);
    }
}
