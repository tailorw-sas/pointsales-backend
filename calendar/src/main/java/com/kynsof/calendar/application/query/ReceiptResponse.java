package com.kynsof.calendar.application.query;

import com.kynsof.calendar.domain.dto.PatientDto;
import com.kynsof.calendar.domain.dto.ReceiptDto;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusReceipt;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

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