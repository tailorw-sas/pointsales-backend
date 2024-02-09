package com.kynsof.scheduled.application.query;

import com.kynsof.scheduled.domain.dto.EStatusReceipt;
import com.kynsof.scheduled.domain.dto.PatientDto;
import com.kynsof.scheduled.domain.dto.ReceiptDto;
import com.kynsof.scheduled.domain.dto.ScheduleDto;
import com.kynsof.scheduled.domain.dto.ServiceDto;
import com.kynsof.scheduled.infrastructure.config.bus.query.IResponse;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReceiptResponse implements IResponse {
    private UUID id;
    private Double price;
    private Boolean express;
    private String reasons;
    private PatientDto user;
    private ScheduleDto schedule;
    private ServiceDto service;
    private EStatusReceipt status;

    public ReceiptResponse(ReceiptDto object) {
        this.id = object.getId();
        this.price = object.getPrice();
        this.express = object.getExpress();
        this.reasons = object.getReasons();
        this.user = object.getUser();
        this.schedule = object.getSchedule();
        this.service = object.getService();
        this.status = object.getStatus();
    }

}