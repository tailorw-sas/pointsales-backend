package com.kynsof.calendar.application.query.businessService.getbyid;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.dto.BusinessServicesDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class BusinessServicesResponse implements IResponse {
    private UUID id;
    private BusinessDto business;
    private ServiceDto service;
    private LocalDateTime createdAt;

    public BusinessServicesResponse(BusinessServicesDto businessServicesDto) {
        this.id = businessServicesDto.getId();
        this.business = businessServicesDto.getBusiness();
        this.service = businessServicesDto.getService();
        this.createdAt = businessServicesDto.getCreatedAt();
    }

}
