package com.kynsof.shift.application.query.resource.getServiceByBusinessIdByResourceId;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class getServiceByBusinessIdByResourceIdQuery implements IQuery {

    private final UUID resourceId;
    private final UUID businessId;

    public getServiceByBusinessIdByResourceIdQuery(UUID resourceId, UUID businessId) {
        this.resourceId = resourceId;
        this.businessId = businessId;
    }

}
