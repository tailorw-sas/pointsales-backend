package com.kynsof.calendar.infrastructure.entity;

import com.kynsof.calendar.domain.dto.ReceiptDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusReceipt;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
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

    private Double price;
    private Boolean express;

    @Size(max = 200)
    private String reasons;

    @ManyToOne
    @JoinColumn(name = "fk_pk_user")
    private Patient user;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_pk_schedule")
    private Schedule schedule;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_pk_service")
    private Services service;

    @Enumerated(EnumType.STRING)
    private EStatusReceipt status;

    private String requestId;
    private String authorizationCode;
    private String reference;
    private String sessionId;
    private String ipAddressCreate;
    private String ipAddressPayment;
    private String userAgentCreate;
    private String userAgentPayment;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime paymentDate;

    @Transient
    private EStatusReceipt previousStatus;

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
        this.ipAddressCreate = receipt.getIpAddressCreate();
        this.ipAddressPayment = receipt.getIpAddressPayment();
        this.userAgentCreate = receipt.getUserAgentCreate();
        this.userAgentPayment = receipt.getUserAgentPayment();
    }

    public ReceiptDto toAggregate() {
        return new ReceiptDto(id, price, express, reasons, user.toAggregate(), schedule.toAggregate(),
                service.toAggregate(), status, requestId, authorizationCode, reference,sessionId,ipAddressCreate,
                ipAddressPayment,userAgentCreate, userAgentPayment);
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    public void onLoad() {
        previousStatus = this.status;
    }
    @PreUpdate
    public void onPreUpdate() {
        if (EStatusReceipt.APPROVED.equals(status) && !status.equals(previousStatus)) {
            paymentDate = LocalDateTime.now();
        }
    }
}
