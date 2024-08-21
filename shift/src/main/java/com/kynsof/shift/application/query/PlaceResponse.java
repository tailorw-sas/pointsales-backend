package com.kynsof.shift.application.query;

import com.kynsof.shift.domain.dto.PlaceDto;
import com.kynsof.shift.domain.dto.enumType.EServiceStatus;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class PlaceResponse implements IResponse {
    private UUID id;
    private String name;
    private String code;
    private EServiceStatus status;
    private BlockResponse block;


    public PlaceResponse(PlaceDto object) {
        this.id = object.getId();
        this.name = object.getName();
        this.code = object.getCode();
        this.status = object.getStatus();
        this.block = new BlockResponse(object.getBlock());
    }

}