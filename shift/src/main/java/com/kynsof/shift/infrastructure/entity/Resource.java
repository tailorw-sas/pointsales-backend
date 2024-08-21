package com.kynsof.shift.infrastructure.entity;

import com.kynsof.shift.domain.dto.ResourceDto;
import com.kynsof.shift.domain.dto.enumType.EResourceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Resource {

    @Id
    protected UUID id;

    @Size(max = 250)
    @NotBlank
    private String name;

    @Column(nullable = true)
    private String code;

    @Enumerated(EnumType.STRING)
    private EResourceStatus status;

    private String image;

    @OneToMany(mappedBy = "resource")
    private Set<ResourceService> resourceServices = new HashSet<>();

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BusinessResource> businessResources = new HashSet<>();

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Turn> turns = new HashSet<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Resource(ResourceDto resourceDto) {
        this.id = resourceDto.getId();
        this.name = resourceDto.getName();
        this.image = resourceDto.getImage();
        this.status = resourceDto.getStatus();
        this.code = resourceDto.getCode();
        //services = resourceDto.getServices() != null ? resourceDto.getServices().stream().map(Services::new).collect(Collectors.toSet()) : null;
    }

    public ResourceDto toAggregate() {
        // List<ServiceDto> serviceDtos = services.stream().map(Services::toAggregate).toList();
        return new ResourceDto(id, name, image, status, code);
    }
}
