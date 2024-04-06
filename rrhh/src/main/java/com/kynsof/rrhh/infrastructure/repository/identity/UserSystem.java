package com.kynsof.rrhh.infrastructure.repository.identity;

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
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
//@Audited
//@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_system")
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
    private String status;
    @Enumerated(EnumType.STRING)
    private String userType;
    private UUID image;


    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

//    public UserSystem(UserSystemDto dto) {
//        this.id = dto.getId();
//        this.userName = dto.getUserName();
//        this.email = dto.getEmail();
//        this.name = dto.getName();
//        this.lastName = dto.getLastName();
//        this.status = dto.getStatus();
//        this.userType = dto.getUserType() != null ? dto.getUserType() : UserType.UNDEFINED;
//        this.image = dto.getIdImage() != null ? dto.getIdImage() : null;
//    }

}
