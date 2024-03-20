package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.dto.enumType.PermissionStatusEnm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Permission {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(unique = true)
    private String code;
    private String description;
    @Enumerated(EnumType.STRING)
    private PermissionStatusEnm status;

    @OneToMany(mappedBy = "permission")
    private List<RolePermission> rolPermissions = new ArrayList<>();

    public Permission(PermissionDto permissionDto) {
        this.id = permissionDto.getId();
        this.code = permissionDto.getCode();
        this.description = permissionDto.getDescription();
        this.status = permissionDto.getStatus();
    }

    public PermissionDto toAggregate() {
        return new PermissionDto(this.id, this.code, this.description, this.status);
    }
}
