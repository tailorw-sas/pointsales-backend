package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_permission_business")
public class UserPermissionBusiness {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserSystem user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_id")
    private Permission permission;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_id")
    private Business business;

    private boolean deleted;

    public UserPermissionBusiness(UUID id, UserSystemDto user, PermissionDto permissionDto, BusinessDto business) {
        this.id = id;
        this.user = new UserSystem(user);
        this.permission = new Permission(permissionDto);
        this.business = new Business(business);
    }

    public UserPermissionBusiness(UserPermissionBusinessDto userRoleBusinessDto) {
        this.id = userRoleBusinessDto.getId();
        this.user = new UserSystem(userRoleBusinessDto.getUser());
        this.permission = new Permission(userRoleBusinessDto.getPermission());
        this.business = new Business(userRoleBusinessDto.getBusiness());
        this.deleted = userRoleBusinessDto.isDeleted();
    }

    public UserPermissionBusinessDto toAggregate () {
        return new UserPermissionBusinessDto(id, user.toAggregate(), permission.toAggregate(), business.toAggregate());
    }
}
