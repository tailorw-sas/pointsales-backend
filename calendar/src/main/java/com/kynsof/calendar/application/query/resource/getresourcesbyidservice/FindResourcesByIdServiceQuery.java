package com.kynsof.calendar.application.query.resource.getresourcesbyidservice;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Getter
public class FindResourcesByIdServiceQuery  implements IQuery {

    private UUID id;
    private Pageable pageable;

    public FindResourcesByIdServiceQuery(UUID id, Pageable pageable) {
        this.id = id;
        this.pageable = pageable;
    }

}
