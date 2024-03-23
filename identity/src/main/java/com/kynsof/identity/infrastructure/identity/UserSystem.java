package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.dto.UserStatus;
import com.kynsof.identity.domain.dto.UserSystemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
//@Audited
//@EntityListeners(AuditingEntityListener.class)
public class UserSystem implements Serializable {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(unique = true)
    private String userName;
    @Column(unique = true)
    private String email;
    private String name;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    // Relationship with User_Role_Clinic
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRoleBusiness> userRolesClinics = new HashSet<>();

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public UserSystem(UserSystemDto dto) {
        this.id = dto.getId();
        this.userName = dto.getUserName();
        this.email = dto.getEmail();
        this.name = dto.getName();
        this.lastName = dto.getLastName();
        this.status = dto.getStatus();
    }

    public UserSystemDto toAggregate() {
        List<RoleDto> rolDtos = new ArrayList<>();
//        if (!roles.isEmpty()) {
//            rolDtos = this.roles.stream()
//                    .map(userRol -> userRol.toAggregate())
//                    .toList();
//        }

        return new UserSystemDto(this.id, this.userName, this.email, this.name, this.lastName, this.status, rolDtos);
    }
}
