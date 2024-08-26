package com.kynsof.calendar.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusSchedule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column
    private LocalDate date;

    @Column
    private LocalTime startTime;

    @Column
    private LocalTime endingTime;

    @Column
    private int stock;

    @JsonIgnore
    private int initialStock;

    @Column
    @Enumerated(EnumType.STRING)
    private EStatusSchedule status;

    @JsonIgnoreProperties({"picture", "services", "qualifications"})
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @JsonIgnoreProperties({"logo", "description", "resources", "services", "schedules", "receipts"})
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "business_id")
    private Business business;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id")
    private Services service;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("schedule") // Evita la recursión infinita al serializar a JSON
    private List<Receipt> receipts = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Schedule(ScheduleDto scheduleDto) {
        this.id = scheduleDto.getId();
        this.resource = new Resource(scheduleDto.getResource());
        this.business = new Business(scheduleDto.getBusiness());
        this.date = scheduleDto.getDate();
        this.startTime = scheduleDto.getStartTime();
        this.endingTime = scheduleDto.getEndingTime();
        this.stock = scheduleDto.getStock();
        this.initialStock = scheduleDto.getInitialStock();
        this.status = scheduleDto.getStatus();
        this.service = new Services(scheduleDto.getService());
    }

    public ScheduleDto toAggregate() {
        return new ScheduleDto(id, resource.toAggregate(), business.toAggregate(), date, startTime, endingTime, stock,
                initialStock, status, service.toAggregate());
    }

}
