package com.kynsof.rrhh.application.query.device.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindDeviceByIdQuery  implements IQuery {

    private UUID id;

    public FindDeviceByIdQuery(UUID id) {
        this.id = id;
    }

}
