package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.dto.RoleStatusEnm;
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
@Data
@Audited
public class RoleSystem {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(unique = true)
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private RoleStatusEnm status;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles = new ArrayList<>();

    @OneToMany(mappedBy = "role")
    private List<RolePermission> rolPermissions = new ArrayList<>();

    public RoleSystem(RoleDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.status = dto.getStatus();
    }

    public RoleDto toAggregate() {
        return new RoleDto(this.id, this.name, this.description, this.status);
    }
}
