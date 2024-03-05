package com.kynsof.store.application.query.product;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindProductsByIdQuery implements IQuery {
    private final UUID id;

    public FindProductsByIdQuery(UUID id) {
        this.id = id;
    }
}
