package com.kynsof.rrhh.infrastructure.identity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kynsof.rrhh.domain.dto.UserSystemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_system")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
public class UserSystem implements Serializable {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String identification;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "userSystem", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserBusinessRelation> userBusinessRelations;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    @JsonIgnore
    private UserSystemImage image;

    public UserSystem(UserSystemDto dto) {
        this.id = dto.getId();
        this.identification = dto.getIdentification();
        this.email = dto.getEmail();
        this.name = dto.getName();
        this.lastName = dto.getLastName();
        this.status = dto.getStatus();
        this.image = dto.getImage() != null ? new UserSystemImage(dto.getImage()) : null;
    }

    public UserSystemDto toAggregate() {

        //UserSystemImageDto i = this.image != null ? this.image.toAggregate() : null;
        return new UserSystemDto(this.id, this.identification, this.email,
                this.name, this.lastName, this.status);
    }
}
