package com.kynsof.scheduled.infrastructure.dao;

import com.kynsof.scheduled.domain.EStatusReceipt;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Receipt {
        
    @Id
    private UUID id;

    @Column(nullable = true)
    private Double price;

    @Column(nullable = true)
    private Boolean express;

    @Size(max = 200)
    @Column(nullable = true)
    private String reasons;

    @ManyToOne
    @JoinColumn(name = "fk_pk_user")
    private User user;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_pk_schedule", unique = false)
    private Schedule schedule;

    //@JsonIgnore
    @OneToOne
    @JoinColumn(name = "fk_pk_service", unique = false)
    private Service service;
    
    @Column
    private EStatusReceipt status;

    public Receipt(User user, Schedule schedule, Service service) {
        this.user = user;
        this.schedule = schedule;
        this.service = service;
    }

}
