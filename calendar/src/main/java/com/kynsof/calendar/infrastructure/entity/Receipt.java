package com.kynsof.calendar.infrastructure.entity;

import com.kynsof.calendar.domain.dto.ReceiptDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusReceipt;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Receipt {
        
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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
    private Patient user;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_pk_schedule", unique = false)
    private Schedule schedule;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_pk_service", unique = false)
    private Services service;

    @Enumerated(EnumType.STRING)
    private EStatusReceipt status;

    private String requestId;
    private String authorizationCode;
    private String reference;
    private String sessionId;


    public Receipt(ReceiptDto receipt) {
        this.id = receipt.getId();
        this.price = receipt.getPrice();
        this.express = receipt.getExpress();
        this.reasons = receipt.getReasons();
        this.status = receipt.getStatus();
        this.user = new Patient(receipt.getUser());
        this.schedule = receipt.getSchedule() != null ? new Schedule(receipt.getSchedule()) : null;
        this.service = new Services(receipt.getService());
        this.requestId = receipt.getRequestId();
        this.authorizationCode = receipt.getAuthorizationCode();
        this.reference = receipt.getReference();
        this.sessionId = receipt.getSessionId();
    }

    public ReceiptDto toAggregate() {
        return new ReceiptDto(id, price, express, reasons, user.toAggregate(), schedule.toAggregate(),
                service.toAggregate(), status, requestId, authorizationCode, reference,sessionId);
    }
}
