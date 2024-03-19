package com.kynsof.calendar.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusSchedule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Schedule {

    @Id
    private UUID id;

    @Column
    private LocalDate date;

    @Column
    private LocalTime startTime;

    @Column
    private LocalTime endingTime;

    @JsonIgnore
    @Column
    private int stock;

    @JsonIgnore
    @Column(updatable = false, nullable = true)
    private int initialStock;

    @Column
    private EStatusSchedule status;

    @JsonIgnoreProperties({"picture", "services", "qualifications"})
    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_pk_resource", nullable = true)
    private Resource resource;

    @JsonIgnoreProperties({"logo", "description", "resources", "services", "schedules", "receipts"})
    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "business_id", nullable = true)
    private Business business;

    @PrePersist
    public void prePersist() {
        this.initialStock = this.stock;
    }

    public Schedule(LocalDate date, LocalTime startTime, LocalTime endingTime) {
        this.date = date;
        this.startTime = startTime;
        this.endingTime = endingTime;
    }

    public Schedule(UUID id, LocalDate date, LocalTime startTime, LocalTime endingTime, EStatusSchedule status) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endingTime = endingTime;
        this.status = status;
    }

    public Schedule(UUID id, LocalDate date, LocalTime startTime, LocalTime endingTime, Resource resource) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endingTime = endingTime;
        this.resource = resource;
    }

    public Schedule(LocalDate date, LocalTime startTime, LocalTime endingTime, Resource resource) {
        this.date = date;
        this.startTime = startTime;
        this.endingTime = endingTime;
        this.resource = resource;
    }

    public Schedule(ScheduleDto scheduleDto) {
        this.id = scheduleDto.getId();
        this.resource = new Resource(scheduleDto.getResource());
        this.business = new Business(scheduleDto.getBusiness());
        this.date = scheduleDto.getDate();
        this.startTime = scheduleDto.getStartTime();
        this.endingTime = scheduleDto.getEndingTime();
        this.stock = scheduleDto.getStock();
        this.status = scheduleDto.getStatus();
    }

    public ScheduleDto toAggregate () {
        return new ScheduleDto(id, resource.toAggregate(), business.toAggregate(), date, startTime, endingTime, stock, initialStock, status);
    }

}
