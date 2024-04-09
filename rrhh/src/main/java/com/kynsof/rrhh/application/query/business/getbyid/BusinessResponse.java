package com.kynsof.rrhh.application.query.business.getbyid;

import com.kynsof.rrhh.doman.dto.BusinessDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class BusinessResponse implements IResponse {
    private UUID id;
    private String name;

    public BusinessResponse(BusinessDto object) {
        this.id = object.getId();
        this.name = object.getName();
    }

}