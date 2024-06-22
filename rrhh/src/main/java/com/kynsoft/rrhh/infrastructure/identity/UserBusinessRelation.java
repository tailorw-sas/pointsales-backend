package com.kynsoft.rrhh.infrastructure.identity;

import com.kynsoft.rrhh.domain.dto.UserBusinessRelationDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "user_business_relation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBusinessRelation {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserSystem userSystem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_id")
    private Business business;

    @Column(name = "state")
    private String state;

    @Column(name = "date")
    private LocalDateTime date;

    @CreationTimestamp
    @Column(nullable = true, updatable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true, updatable = true)
    private LocalDateTime updatedAt;

    public UserBusinessRelation(UserBusinessRelationDto userBusinessRelation) {
        this.id = userBusinessRelation.getId();
        this.userSystem = new UserSystem(userBusinessRelation.getUserSystem());
        this.business = new Business(userBusinessRelation.getBusiness());
        this.state = userBusinessRelation.getState();
        this.date = userBusinessRelation.getDate();
    }

    public UserBusinessRelationDto toAggregate () {
        return new UserBusinessRelationDto(id, 
                                           userSystem.toAggregate(),
                                           business.toAggregate(),
                                           state,
                                           date);
    }

}
