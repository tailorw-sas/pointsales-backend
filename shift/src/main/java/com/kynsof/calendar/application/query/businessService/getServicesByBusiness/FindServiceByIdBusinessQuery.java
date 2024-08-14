package com.kynsof.calendar.application.query.businessService.getServicesByBusiness;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Getter
public class FindServiceByIdBusinessQuery  implements IQuery {

    private final UUID id;
    private final Pageable pageable;

    public FindServiceByIdBusinessQuery(UUID id, Pageable pageable) {
        this.id = id;
        this.pageable = pageable;
    }

}
