package com.kynsof.calendar.infrastructure.entity;

import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceType {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "type")
    private Set<Services> services;

    public ServiceType(ServiceTypeDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
    }

    public ServiceTypeDto toAggregate() {
        return new ServiceTypeDto(this.id, this.name);
    }
}
