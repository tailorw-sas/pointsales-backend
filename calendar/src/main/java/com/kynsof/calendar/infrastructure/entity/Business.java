package com.kynsof.calendar.infrastructure.entity;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.dto.enumType.EBusinessStatus;
import com.kynsof.share.core.domain.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Business extends BaseEntity {

    private String name;

    private String latitude;

    private String longitude;

    private String description;

    @Lob
    private byte[] logo;

    private String ruc;

    @Enumerated(EnumType.STRING)
    private EBusinessStatus status;

    @Column(nullable = true)
    private boolean deleted;

    // Relación de muchos a muchos con Resource
    @ManyToMany(mappedBy = "businesses", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Resource> resources = new HashSet<>();

   // @JsonIgnoreProperties("businesses")
    @ManyToMany(mappedBy = "businesses", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Services> services = new HashSet<>();

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
        return new BusinessDto(id, name, latitude, longitude, description, logo, ruc, status, deleted, createdAt, updatedAt, deletedAt);
    }
}
