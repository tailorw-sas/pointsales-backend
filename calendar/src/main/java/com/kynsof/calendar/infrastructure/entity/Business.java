package com.kynsof.calendar.infrastructure.entity;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.dto.enumType.EBusinessStatus;
import com.kynsof.share.core.domain.BaseEntity;
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
public class Business{
    @Id
    @Column(name="id")
    private UUID id;


    private String name;

    private String latitude;

    private String longitude;

    private String description;

    @Lob
    private byte[] logo;
    private UUID idLogo;
    private String ruc;

    @Enumerated(EnumType.STRING)
    private EBusinessStatus status;

    @Column(nullable = true)
    private boolean deleted;

    // Relación de muchos a muchos con Resource
    @ManyToMany(mappedBy = "businesses")
    private Set<Resource> resources = new HashSet<>();

   // @JsonIgnoreProperties("businesses")
    @ManyToMany(mappedBy = "businesses")
    private Set<Services> services = new HashSet<>();

    public Business(BusinessDto business) {
        this.id = business.getId();
        this.name = business.getName();
        this.latitude = business.getLatitude();
        this.longitude = business.getLongitude();
        this.description = business.getDescription();
        this.idLogo = business.getIdLogo();
        this.ruc = business.getRuc();
        this.status = business.getStatus();
        this.deleted = business.isDeleted();
    }

    public BusinessDto toAggregate () {
        return new BusinessDto(id, name, latitude, longitude, description, idLogo, ruc, status, deleted);
    }
}
