package com.kynsof.rrhh.infrastructure.identity;

import com.kynsof.rrhh.doman.dto.UserSystemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_system")
@Getter
@Setter
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


    public UserSystemDto toAggregate() {

        return new UserSystemDto(this.id, this.identification, this.email,
                this.name, this.lastName, this.status);
    }
}
