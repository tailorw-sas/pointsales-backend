package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.ModuleDto;
import com.kynsof.identity.domain.dto.PermissionDto;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class SystemModule {

    @Id
    @Column(name = "id")
    protected UUID id;
    private String name;
    private UUID image;
    private String description;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Permission> permissions = new HashSet<>();

    public SystemModule(ModuleDto module) {
        this.id = module.getId();
        this.name = module.getName();
        this.image = module.getImage();
        this.description = module.getDescription();
    }

    public ModuleDto toAggregate () {
        Set<PermissionDto> p = new HashSet<>();
        for (Permission permission : permissions) {
            p.add(new PermissionDto(permission.getId(), permission.getCode(), permission.getDescription()));
        }
        return new ModuleDto(id, name, image, description, p);
    }
}
