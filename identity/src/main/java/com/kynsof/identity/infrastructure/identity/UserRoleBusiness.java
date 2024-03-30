package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.BusinessDto;
import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.dto.UserRoleBusinessDto;
import com.kynsof.identity.domain.dto.UserSystemDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_role_business")
public class UserRoleBusiness {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserSystem user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleSystem role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_id")
    private Business business;

    @Column(nullable = true)
    private boolean deleted;

    public UserRoleBusiness(UUID id, UserSystemDto user, RoleDto role, BusinessDto business) {
        this.id = id;
        this.user = new UserSystem(user);
        this.role = new RoleSystem(role);
        this.business = new Business(business);
    }

    public UserRoleBusiness(UserRoleBusinessDto userRoleBusinessDto) {
        this.id = userRoleBusinessDto.getId();
        this.user = new UserSystem(userRoleBusinessDto.getUser());
        this.role = new RoleSystem(userRoleBusinessDto.getRole());
        this.business = new Business(userRoleBusinessDto.getBusiness());
        this.deleted = userRoleBusinessDto.isDeleted();
    }

    public UserRoleBusinessDto toAggregate () {
        return new UserRoleBusinessDto(id, user.toAggregate(), role.toAggregate(), business.toAggregate());
    }
}
