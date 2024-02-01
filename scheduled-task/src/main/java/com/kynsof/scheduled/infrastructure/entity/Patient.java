package com.kynsof.scheduled.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class Patient {
    @Id
    @Column(name="user_id")
    private UUID id;

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Size(max = 50)
    private String email;

    @JsonIgnore
    @NotBlank
    @Size(max = 120)
    private String password;

    private boolean active;

    private boolean verified;

    @Size(max = 500)
    private String fcm_token;

    @Size(max = 10)
    private String otp;



    public Patient() {
    }

    public Patient(UUID id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = true;
        this.verified = true;
    }

}
