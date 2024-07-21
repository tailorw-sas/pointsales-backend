package com.kynsof.calendar.infrastructure.entity;


import com.kynsof.calendar.domain.dto.TurnDto;
import com.kynsof.calendar.domain.dto.enumType.EPriority;
import com.kynsof.calendar.domain.dto.enumType.ETurnStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Turn {

    @Id
    @Column(name = "id")
    protected UUID id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "services_id")
    private Services services;

    @Column(name = "identification", nullable = false)
    private String identification;

    @Column(name = "order_number", nullable = false)
    private Integer orderNumber;

    private Integer position;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private EPriority priority;

    @Column(name = "is_preferential", nullable = false)
    private Boolean isPreferential;

    @Column(name = "waiting_time")
    private String waitingTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ETurnStatus status;

    private Boolean isNeedPayment;
    private UUID nextServices;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    private String local;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Turn(TurnDto turnDto) {
        this.id = turnDto.getId();
        this.resource = turnDto.getDoctor() != null ? new Resource(turnDto.getDoctor()) : null;
        this.services = new Services(turnDto.getServices());
        this.identification = turnDto.getIdentification();
        this.orderNumber = turnDto.getOrderNumber();
        this.priority = turnDto.getPriority();
        this.isPreferential = turnDto.getIsPreferential();
        this.waitingTime = turnDto.getWaitingTime();
        this.status = turnDto.getStatus();
        this.business = turnDto.getBusiness() != null ? new Business(turnDto.getBusiness()) : null;
        this.local = turnDto.getLocal();
        this.isNeedPayment = turnDto.getIsNeedPayment();
        this.nextServices = turnDto.getNextServices();
    }

    public TurnDto toAggregate() {
        TurnDto turnDto = new TurnDto(
                id,
                resource != null ? resource.toAggregate() : null,
                services.toAggregate(),
                identification,
                orderNumber,
                priority,
                isPreferential,
                waitingTime,
                status,
                business != null ? business.toAggregate() : null,
                position,
                isNeedPayment
        );
        turnDto.setNextServices(nextServices);
        turnDto.setLocal(local);
        turnDto.setCreateAt(createdAt);
        return turnDto;
    }

    public void setStatus(ETurnStatus status) {
        this.status = status;
        calculateWaitingTime();

    }

    private void calculateWaitingTime() {
        if (this.createdAt != null) {
            Duration duration = Duration.between(this.createdAt, LocalDateTime.now());
            long minutes = duration.toMinutes();
            this.waitingTime = minutes + " min";
        }
    }
}