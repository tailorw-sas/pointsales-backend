package com.kynsof.shift.application.query;

import com.kynsof.shift.domain.dto.BusinessDto;
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
    private String latitude;
    private String longitude;
    private String address;
    private String logo;

    public BusinessResponse(BusinessDto object) {
        this.id = object.getId();
        this.name = object.getName();
        this.latitude = object.getLatitude();
        this.longitude = object.getLongitude();
        this.logo = object.getLogo();
        this.address = object.getAddress();
    }

}