package com.kynsof.shift.domain.dto;


import com.kynsof.shift.domain.dto.enumType.ETurnStatus;
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
    private String waitingTime;
    private ETurnStatus status;
    private BusinessDto business;
    private LocalDateTime createAt;
    private String Local;

    public TurnDto(UUID id, ResourceDto doctorId, ServiceDto specialtyCode, String identification,
                   Integer orderNumber,
                   String waitingTime, ETurnStatus status,
                   BusinessDto business) {
        this.id = id;
        this.doctor = doctorId;
        this.services = specialtyCode;
        this.identification = identification;
        this.orderNumber = orderNumber;
        this.waitingTime = waitingTime;
        this.status = status;
        this.business = business;
    }
}