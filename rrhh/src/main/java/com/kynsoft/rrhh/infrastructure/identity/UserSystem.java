package com.kynsoft.rrhh.infrastructure.identity;

import com.kynsoft.rrhh.domain.dto.UserSystemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
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

    private String phoneNumber;

    @Column(nullable = true)
    private String image;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "userSystem", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserBusinessRelation> userBusinessRelations;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public UserSystem(UserSystemDto dto) {
        this.id = dto.getId();
        this.identification = dto.getIdentification();
        this.email = dto.getEmail();
        this.name = dto.getName();
        this.lastName = dto.getLastName();
        this.status = dto.getStatus();
        this.image = dto.getImage();
        this.phoneNumber = dto.getPhoneNumber();
    }

    public UserSystemDto toAggregate() {
        return new UserSystemDto(this.id, this.identification, this.email,
                this.name, this.lastName, this.status,this.phoneNumber,this.image );
    }
}
