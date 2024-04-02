package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.dto.enumType.RoleStatusEnm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "role_system")
public class RoleSystem {

    @Id
    @Column(name = "id")
    private UUID id;
    @Column(unique = true)
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private RoleStatusEnm status;

    @Column(nullable = true)
    private boolean deleted;

    // Relationships
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserRoleBusiness> userRoleBusinesses = new HashSet<>();

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<RolePermission> rolePermissions = new HashSet<>();

    public RoleSystem(RoleDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.status = dto.getStatus();
        this.deleted = dto.isDeleted();
    }

    public RoleDto toAggregate() {
        List<PermissionDto> permissionDtos = rolePermissions.stream()
                .filter(rp -> !rp.isDeleted())
                .map(rolePermission -> rolePermission.getPermission().toAggregate())
                .toList();
        return new RoleDto(this.id, this.name, this.description, this.status, permissionDtos);
    }
}
