package com.kynsof.scheduled.infrastructure.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kynsof.scheduled.domain.EStatusSchedule;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "fk_pk_specialist", nullable = true)
    private Specialist specialist;

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

    public Schedule(UUID id, LocalDate date, LocalTime startTime, LocalTime endingTime, Specialist specialist) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endingTime = endingTime;
        this.specialist = specialist;
    }

    public Schedule(LocalDate date, LocalTime startTime, LocalTime endingTime, Specialist specialist) {
        this.date = date;
        this.startTime = startTime;
        this.endingTime = endingTime;
        this.specialist = specialist;
    }

}
