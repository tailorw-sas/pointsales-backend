package com.kynsof.shift.application.query.place.getById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindPlaceByIdQuery implements IQuery {

    private UUID id;

    public FindPlaceByIdQuery(UUID id) {
        this.id = id;
    }

}
