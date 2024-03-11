package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.RolDto;
import com.kynsof.identity.domain.dto.Status;
import com.kynsof.identity.domain.dto.UserSystemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class UserSystem implements Serializable {
    @Id
    @Column(name="id")
    private UUID id;
    @Column(unique = true)
    private String userName;
    @Column(unique = true)
    private String email;
    private String name;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRol> userRols = new ArrayList<>();

    public UserSystem(UserSystemDto dto) {
        this.id = dto.getId();
        this.userName = dto.getUserName();
        this.email = dto.getEmail();
        this.name = dto.getName();
        this.lastName = dto.getLastName();
        this.status = dto.getStatus();
    }

    public UserSystemDto toAggregate() {
        List<RolDto> rolDtos = this.userRols.stream()
                .map(userRol -> userRol.getRol().toAggregate())
                .collect(Collectors.toList());

        return new UserSystemDto(this.id, this.userName, this.email, this.name, this.lastName, this.status, rolDtos);
    }
}
