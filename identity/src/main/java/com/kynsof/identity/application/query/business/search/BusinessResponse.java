package com.kynsof.identity.application.query.business.search;

import com.kynsof.identity.application.query.business.geographiclocation.getall.GeographicLocationResponse;
import com.kynsof.identity.domain.dto.BusinessDto;
import com.kynsof.identity.domain.dto.enumType.EBusinessStatus;
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
    private String description;
    private String logo;
    private String ruc;
    private String address;
    private EBusinessStatus status;

    private GeographicLocationResponse geolocation;

    public BusinessResponse(BusinessDto object) {
        this.id = object.getId();
        this.name = object.getName();
        this.latitude = object.getLatitude();
        this.longitude = object.getLongitude();
        this.description = object.getDescription();
        this.logo = object.getLogo();
        this.ruc = object.getRuc();
        this.status = object.getStatus();
        this.geolocation = object.getGeographicLocationDto() != null ? new GeographicLocationResponse(object.getGeographicLocationDto()) : null;
        this.address = object.getAddress() != null ? object.getAddress() : null;
    }

    public BusinessResponse(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

}