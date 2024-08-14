package com.kynsof.calendar.application.query;

import com.kynsof.calendar.domain.dto.BlockDto;
import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class BlockResponse implements IResponse {
    private UUID id;
    private String name;
    private String code;
    private EServiceStatus status;


    public BlockResponse(BlockDto object) {
        this.id = object.getId();
        this.name = object.getName();
        this.code = object.getCode();
        this.status = object.getStatus();
    }

}