package com.kynsof.shift.application.query.resource.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindResourceByIdQuery  implements IQuery {

    private final UUID id;

    public FindResourceByIdQuery(UUID id) {
        this.id = id;
    }

}
