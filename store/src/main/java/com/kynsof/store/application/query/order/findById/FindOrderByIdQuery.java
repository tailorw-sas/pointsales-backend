package com.kynsof.store.application.query.order.findById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindOrderByIdQuery implements IQuery {
    private final UUID id;

    public FindOrderByIdQuery(UUID id) {
        this.id = id;
    }
}
