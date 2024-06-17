package com.kynsoft.rrhh.application.query.business.getbyid;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsoft.rrhh.domain.dto.BusinessDto;
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