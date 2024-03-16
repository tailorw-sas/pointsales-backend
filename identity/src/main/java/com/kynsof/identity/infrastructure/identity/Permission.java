package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.PermissionStatusEnm;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
//@Data
//@Audited
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
}
