package com.kynsof.shift.application.query.resource.getresourcesbyidservice;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Getter
public class FindResourcesByIdServiceQuery  implements IQuery {

    private final UUID businessId;
    private final UUID serviceId;
    private final Pageable pageable;

    public FindResourcesByIdServiceQuery(UUID businessId, UUID serviceId, Pageable pageable) {
        this.businessId = businessId;
        this.serviceId = serviceId;
        this.pageable = pageable;
    }

}
