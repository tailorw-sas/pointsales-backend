package com.kynsof.scheduled.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Scheduled {

    private UUID id;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endingTime;

    private int stock;

    private int initialStock;

    private EStatusSchedule status;

}
