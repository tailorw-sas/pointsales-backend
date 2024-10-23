package com.kynsof.treatments.application.query.typeService.replicate;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.treatments.domain.dto.ServiceTypeDto;
import com.kynsof.treatments.domain.dto.enumDto.EServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ServiceTypeResponse implements IResponse {
    private UUID id;
    private String name;
    private String image;
    private String code;
    private EServiceStatus status;


    public ServiceTypeResponse(ServiceTypeDto object) {
        this.id = object.getId();
        this.name = object.getName();
        this.image = object.getPicture();
        this.code = object.getCode();
        this.status = object.getStatus();
    }

}