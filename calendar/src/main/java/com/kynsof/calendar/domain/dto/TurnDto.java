package com.kynsof.calendar.domain.dto;


import com.kynsof.calendar.domain.dto.enumType.EPriority;
import com.kynsof.calendar.domain.dto.enumType.ETurnStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class TurnDto {

    private UUID id;
    private ResourceDto doctor;
    private ServiceDto services;
    private String identification;
    private Integer orderNumber;
    private EPriority priority;
    private Boolean isPreferential;
    private String waitingTime;
    private ETurnStatus status;
    private BusinessDto business;
    private LocalDateTime createAt;

    public TurnDto(UUID id, ResourceDto doctorId,  ServiceDto specialtyCode, String identification,
                   Integer orderNumber, EPriority priority, Boolean isPreferential,
                   String waitingTime, ETurnStatus status,
                   BusinessDto business) {
        this.id = id;
        this.doctor = doctorId;
        this.services = specialtyCode;
        this.identification = identification;
        this.orderNumber = orderNumber;
        this.priority = priority;
        this.isPreferential = isPreferential;
        this.waitingTime = waitingTime;
        this.status = status;
        this.business = business;
    }

    public TurnDto toAggregate() {
        return new TurnDto(
                id,
                doctor,
                services,
                identification,
                orderNumber,
                priority,
                isPreferential,
                waitingTime,
                status,
                business
        );
    }
}