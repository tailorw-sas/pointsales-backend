package com.kynsof.store.application.query.inventory.getById;


import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindInventoryByIdQuery implements IQuery {
    private final UUID id;

    public FindInventoryByIdQuery(UUID id) {
        this.id = id;
    }
}
