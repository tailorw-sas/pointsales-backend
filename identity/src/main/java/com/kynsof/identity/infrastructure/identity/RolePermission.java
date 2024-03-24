package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.RolePermissionDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role_permission")
@Getter
@Setter
@NoArgsConstructor
public class RolePermission {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleSystem role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_id")
    private Permission permission;

    @Column(nullable = true)
    private LocalDateTime deletedAt;

    @Column()
    private boolean deleted;

    public RolePermission(RolePermissionDto entity) {
        this.id = entity.getId();
        this.role = new RoleSystem(entity.getRole());
        this.permission = new Permission(entity.getPermission());
        this.deletedAt = entity.getDeletedAt();
        this.deleted = entity.isDeleted();
    }

    public RolePermissionDto toAggregate() {
        return new RolePermissionDto(
                this.id, 
                this.role.toAggregate(), 
                this.permission.toAggregate(), 
                this.deletedAt, 
                this.deleted
        );
    }
}
