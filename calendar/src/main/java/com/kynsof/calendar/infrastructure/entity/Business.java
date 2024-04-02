package com.kynsof.calendar.infrastructure.entity;

import com.kynsof.calendar.domain.dto.BusinessDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
    private String address;
    private UUID logo;

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
        this.logo = business.getLogo();
        this.address = business.getAddress();

    }

    public BusinessDto toAggregate () {
        return new BusinessDto(id, name, latitude, longitude, address, logo);
    }
}
