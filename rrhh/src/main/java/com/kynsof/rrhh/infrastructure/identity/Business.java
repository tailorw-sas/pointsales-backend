package com.kynsof.rrhh.infrastructure.identity;

import com.kynsof.rrhh.domain.dto.BusinessDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;
@Entity
@Table(name = "business")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Business {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Device> devices;

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserBusinessRelation> userBusinessRelations;
    
    public Business(BusinessDto business) {
        this.id = business.getId();
        this.name = business.getName();
    }

    public BusinessDto toAggregate () {
        return new BusinessDto(id, name);
    }

}
