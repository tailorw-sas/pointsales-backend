package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.dto.enumType.PermissionStatusEnm;
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
public class Permission {

    @Id
    @Column(name = "id")
    private UUID id;
    @Column(unique = true)
    private String code;
    private String description;
    private String action;

    @Enumerated(EnumType.STRING)
    private PermissionStatusEnm status;

    @Column(nullable = true)
    private boolean deleted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_id")
    private ModuleSystem module;

    @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserPermissionBusiness> userPermissionBusinesses = new HashSet<>();

    public Permission(PermissionDto permissionDto) {
        this.id = permissionDto.getId();
        this.code = permissionDto.getCode();
        this.description = permissionDto.getDescription();
        this.action = permissionDto.getAction();
        this.module = new ModuleSystem(permissionDto.getModule());
        this.status = permissionDto.getStatus();
        this.deleted = permissionDto.isDeleted();
    }

    public PermissionDto toAggregate() {
        return new PermissionDto(this.id, this.code, this.description, this.module.toAggregate(), this.status, this.action);
    }
}
