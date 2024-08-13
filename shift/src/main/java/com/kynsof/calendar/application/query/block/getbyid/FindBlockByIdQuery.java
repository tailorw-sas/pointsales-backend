package com.kynsof.calendar.application.query.block.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindBlockByIdQuery implements IQuery {

    private UUID id;

    public FindBlockByIdQuery(UUID id) {
        this.id = id;
    }

}
