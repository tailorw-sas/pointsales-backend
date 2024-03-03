package com.kynsof.store.application.query.getById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindProductByIdQuery implements IQuery {
    private final UUID id;

    public FindProductByIdQuery(UUID id) {
        this.id = id;
    }
}
