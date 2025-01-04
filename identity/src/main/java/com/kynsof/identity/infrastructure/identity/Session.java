package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.SessionDto;
import com.kynsof.identity.domain.dto.enumType.ESessionStatus;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "session")
public class Session implements Serializable {
    @Id
    @Column(name = "id")
    private UUID id;

    @OneToOne()
    @JoinColumn(name = "user_system_id", nullable = false)
    private UserSystem userSystem;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ESessionStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Session(SessionDto dto) {
        this.id = dto.getId();
        this.userSystem = new UserSystem(dto.getUserSystemDto());
        this.status = dto.getStatus();
        this.business = new Business(dto.getBusinessDto());
    }

    public SessionDto toAggregate() {
        SessionDto sessionDto = new SessionDto(
                id,
                userSystem.toAggregate(),
                status,
                business.toAggregate()
        );
        sessionDto.setCreateAt(createdAt);
        return sessionDto;
    }
}
