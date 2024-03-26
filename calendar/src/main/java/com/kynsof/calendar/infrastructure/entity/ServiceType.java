package com.kynsof.calendar.infrastructure.entity;

import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private UUID id;

    @Size(max = 150)
    @NotBlank
    private String name;

    private UUID picture;

    @OneToMany(mappedBy = "type")
    private Set<Services> services;

    public ServiceType(ServiceTypeDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.picture = dto.getPicture();
    }

    public ServiceTypeDto toAggregate() {
        return new ServiceTypeDto(this.id, this.name,this.picture);
    }
}
