package com.kynsof.shift.application.query.businessResource.getbyid;

import com.kynsof.shift.domain.dto.BusinessDto;
import com.kynsof.shift.domain.dto.BusinessResourceDto;
import com.kynsof.shift.domain.dto.ResourceDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class BusinessResourceResponse implements IResponse {
    private UUID id;
    private BusinessDto business;
    private ResourceDto resource;
    private LocalDateTime createdAt;

    public BusinessResourceResponse(BusinessResourceDto businessResourceDto) {
        this.id = businessResourceDto.getId();
        this.business = businessResourceDto.getBusiness();
        this.resource = businessResourceDto.getResource();
        this.createdAt = businessResourceDto.getCreatedAt();
    }

}
