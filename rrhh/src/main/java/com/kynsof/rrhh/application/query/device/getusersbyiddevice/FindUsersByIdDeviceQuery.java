package com.kynsof.rrhh.application.query.device.getusersbyiddevice;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Getter
public class FindUsersByIdDeviceQuery  implements IQuery {

    private Pageable pageable;
    private UUID id;

    public FindUsersByIdDeviceQuery(UUID id, Pageable pageable) {
        this.id = id;
        this.pageable = pageable;
    }

}
