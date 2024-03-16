package com.kynsof.identity.infrastructure.identity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
//@Data
//@Audited
public class UserRole {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserSystem user;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private RoleSystem role;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
