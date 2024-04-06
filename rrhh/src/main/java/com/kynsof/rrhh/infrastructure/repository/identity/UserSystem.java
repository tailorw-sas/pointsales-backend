package com.kynsof.rrhh.infrastructure.repository.identity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_system")
public class UserSystem implements Serializable {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String identification;
    private String name;
    private String lastName;
    private UUID image;
    private UUID Fingerprint;
}
