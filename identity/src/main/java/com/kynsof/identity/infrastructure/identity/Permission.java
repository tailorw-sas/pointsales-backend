package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.dto.enumType.PermissionStatusEnm;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Permission {

    @Id
    @Column(name = "id")
    private UUID id;
    @Column(unique = true)
    private String code;
    private String description;
    @Enumerated(EnumType.STRING)
    private PermissionStatusEnm status;

    @Column(nullable = true)
    private boolean deleted;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "permission_roles",
            joinColumns = {
                @JoinColumn(name = "permission_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "rol_id")})
    private Set<RoleSystem> roles = new HashSet<>();

    public Permission(PermissionDto permissionDto) {
        this.id = permissionDto.getId();
        this.code = permissionDto.getCode();
        this.description = permissionDto.getDescription();
        this.status = permissionDto.getStatus();
        this.deleted = permissionDto.isDeleted();
        permissionDto.getRoles().stream()
                .map(RoleSystem::new)
                .forEach(this.roles::add);
    }

    public PermissionDto toAggregate() {
        List<RoleDto> rolDtos = new ArrayList<>();
        if (!roles.isEmpty()) {
            rolDtos = this.roles.stream()
                    .map(userRol -> userRol.toAggregate())
                    .toList();
        }

        return new PermissionDto(this.id, this.code, this.description, this.status, this.deleted, Set.copyOf(rolDtos));
    }
}
