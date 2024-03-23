package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.dto.RoleStatusEnm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
//@Data
//@Audited
public class RoleSystem {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(unique = true)
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private RoleStatusEnm status;

    // Relationships
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRoleBusiness> userRoleBusinesses = new HashSet<>();

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RolePermission> rolePermissions = new HashSet<>();

    public RoleSystem(RoleDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.status = dto.getStatus();
    }

    public RoleDto toAggregate() {
        return new RoleDto(this.id, this.name, this.description, this.status);
    }
}
