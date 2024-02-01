package com.kynsof.scheduled.infrastructure.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kynsof.scheduled.domain.EStatusSchedule;
import com.kynsof.scheduled.domain.Scheduled;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ScheduleDao {
    
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

    @PrePersist
    public void prePersist() {
        this.initialStock = this.stock;
    }

    public ScheduleDao(Scheduled scheduled) {
        this.id = scheduled.getId();
        this.date = scheduled.getDate();
        this.startTime = scheduled.getStartTime();
        this.endingTime = scheduled.getEndingTime();
        this.stock = scheduled.getStock();
    }

    public ScheduleDao(LocalDate date, LocalTime startTime, LocalTime endingTime) {
        this.date = date;
        this.startTime = startTime;
        this.endingTime = endingTime;
    }

    public ScheduleDao(UUID id, LocalDate date, LocalTime startTime, LocalTime endingTime, EStatusSchedule status) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endingTime = endingTime;
        this.status = status;
    }

    public ScheduleDao(UUID id, LocalDate date, LocalTime startTime, LocalTime endingTime) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endingTime = endingTime;
    }

}
