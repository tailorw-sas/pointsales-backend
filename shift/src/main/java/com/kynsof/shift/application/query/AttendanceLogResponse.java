package com.kynsof.shift.application.query;

import com.kynsof.shift.application.query.service.ServicesResponse;
import com.kynsof.shift.domain.dto.AttendanceLogDto;
import com.kynsof.shift.domain.dto.enumType.AttentionLocalStatus;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class AttendanceLogResponse implements IResponse {
    private UUID id;
    private ResourceResponse resource;
    private BusinessResponse business;
    private ServicesResponse service;
    private AttentionLocalStatus status;

    public AttendanceLogResponse(AttendanceLogDto object) {
        this.id = object.getId();
        this.resource = new ResourceResponse(object.getResource());
        this.business = new BusinessResponse(object.getBusiness());
        this.service = new ServicesResponse(object.getService());
        this.status = object.getStatus();
    }

}