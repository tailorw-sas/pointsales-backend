package com.kynsof.calendar.application.query.businesservice.getservicesbybusiness;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;
import org.springframework.data.domain.Pageable;

@Getter
public class FindServiceByIdBusinessQuery  implements IQuery {

    private UUID id;
    private Pageable pageable;

    public FindServiceByIdBusinessQuery(UUID id, Pageable pageable) {
        this.id = id;
        this.pageable = pageable;
    }

}
