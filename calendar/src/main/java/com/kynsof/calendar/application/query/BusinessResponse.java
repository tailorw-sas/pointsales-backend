package com.kynsof.calendar.application.query;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.dto.enumType.EBusinessStatus;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private byte[] logo;
    private String ruc;
    private EBusinessStatus status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    public BusinessResponse(BusinessDto object) {
        this.id = object.getId();
        this.name = object.getName();
        this.latitude = object.getLatitude();
        this.longitude = object.getLongitude();
        this.description = object.getDescription();
        this.ruc = object.getRuc();
        this.status = object.getStatus();
        this.createAt = object.getCreateAt();
        this.updateAt = object.getUpdateAt();
        this.deleteAt = object.getDeleteAt();
    }

    public BusinessResponse(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

}