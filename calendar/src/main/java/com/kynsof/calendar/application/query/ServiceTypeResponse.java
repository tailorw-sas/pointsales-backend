package com.kynsof.calendar.application.query;

import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
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
    private UUID picture;


    public ServiceTypeResponse(ServiceTypeDto object) {
        this.id = object.getId();
        this.name = object.getName();
        this.picture = object.getPicture();
    }

}