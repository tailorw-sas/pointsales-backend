package com.kynsof.shift.application.query;

import com.kynsof.shift.application.query.service.ServicesResponse;
import com.kynsof.shift.domain.dto.BusinessDto;
import com.kynsof.shift.domain.dto.TurnDto;
import com.kynsof.shift.domain.dto.enumType.ETurnStatus;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class TurnResponse implements IResponse {
    private UUID id;
    private ResourceResponse doctor;
    private ServicesResponse services;
    private String identification;
    private Integer orderNumber;
    private Boolean isPreferential;
    private String waitingTime;
    private ETurnStatus status;
    private BusinessDto business;
    private LocalDateTime createAt;


    public TurnResponse(TurnDto object) {
        this.id = object.getId();
        this.doctor = object.getDoctor() != null ? new ResourceResponse(object.getDoctor()) : null;
        this.services = object.getServices() != null ? new ServicesResponse(object.getServices()) : null;
        this.identification = object.getIdentification();
        this.orderNumber = object.getOrderNumber();
        this.waitingTime = object.getWaitingTime();
        this.status = object.getStatus();
        this.business = object.getBusiness();
        this.createAt = object.getCreateAt();
    }

}