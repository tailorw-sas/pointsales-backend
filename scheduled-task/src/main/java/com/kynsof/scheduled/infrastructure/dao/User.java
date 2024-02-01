package com.kynsof.scheduled.infrastructure.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
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

	@ManyToMany(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//	private Set<UserEmotion> userEmotions = new HashSet<UserEmotion>();

	@OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Profile profile;

    public User() {
    }

    public User(UUID id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = true;
        this.verified = true;
    }

}
