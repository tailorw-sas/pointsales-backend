package com.kynsof.identity.infrastructure.identity;

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
public class Rol {
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
}
