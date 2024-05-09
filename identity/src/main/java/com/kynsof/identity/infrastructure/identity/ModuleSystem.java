package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.ModuleDto;
import com.kynsof.identity.domain.dto.PermissionDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "module_system")
public class ModuleSystem {

    @Id
    @Column(name = "id")
    protected UUID id;
    private String name;
    private String image;
    private String description;

    @Column(nullable = true)
    private Boolean deleted = false;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Permission> permissions = new HashSet<>();

    public ModuleSystem(ModuleDto module) {
        this.id = module.getId();
        this.name = module.getName();
        this.image = module.getImage();
        this.description = module.getDescription();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeleted(Boolean deleted) {
        
        this.deleted = deleted == null ? false : deleted;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public ModuleDto toAggregate () {
        List<PermissionDto> p = new ArrayList<>();
        for (Permission permission : permissions) {
            p.add(new PermissionDto(permission.getId(), permission.getCode(), permission.getDescription()));
        }
        
        return new ModuleDto(id, name, image, description, p, createdAt);
    }
}
