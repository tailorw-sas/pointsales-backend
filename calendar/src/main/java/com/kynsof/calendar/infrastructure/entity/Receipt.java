package com.kynsof.calendar.infrastructure.entity;

import com.kynsof.calendar.domain.dto.enumType.EStatusReceipt;
import com.kynsof.calendar.domain.dto.ReceiptDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

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
    private Patient user;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_pk_schedule", unique = false)
    private Schedule schedule;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_pk_service", unique = false)
    private Services service;

    @Column
    private EStatusReceipt status;

    public Receipt(Patient user, Schedule schedule, Services service) {
        this.user = user;
        this.schedule = schedule;
        this.service = service;
    }

    public Receipt(ReceiptDto receipt) {
        this.id = receipt.getId();
        this.price = receipt.getPrice();
        this.express = receipt.getExpress();
        this.reasons = receipt.getReasons();
        this.status = receipt.getStatus();
        this.user = new Patient(receipt.getUser());
        this.schedule = receipt.getSchedule() != null ? new Schedule(receipt.getSchedule()) : null;
        this.service = new Services(receipt.getService());
    }

    public ReceiptDto toAggregate() {
        return new ReceiptDto(id, price, express, reasons, user.toAggregate(), schedule.toAggregate(), service.toAggregate(), status);
    }
}
