package com.kynsof.identity.infrastructure.identity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "role_permission")
@Getter
@Setter
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleSystem role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id")
    private Permission permission;

}
