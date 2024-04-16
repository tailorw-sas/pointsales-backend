package com.kynsof.calendar.application.query.service.getservicesbyidresource;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;
import org.springframework.data.domain.Pageable;

@Getter
public class FindServicesByIdResourceQuery  implements IQuery {

    private UUID id;
    private Pageable pageable;

    public FindServicesByIdResourceQuery(UUID id, Pageable pageable) {
        this.id = id;
        this.pageable = pageable;
    }

}
