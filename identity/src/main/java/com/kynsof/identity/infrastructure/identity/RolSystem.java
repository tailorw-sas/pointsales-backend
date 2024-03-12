package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.RolDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class RolSystem {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(unique = true)
    private String name;
    private String description;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRol> userRoles = new ArrayList<>();

    @OneToMany(mappedBy = "rol")
    private List<RolPermission> rolPermissions = new ArrayList<>();

    public RolSystem(RolDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.description = dto.getDescription();
    }

    public RolDto toAggregate() {
        return new RolDto(this.id, this.name, this.description);
    }
}
