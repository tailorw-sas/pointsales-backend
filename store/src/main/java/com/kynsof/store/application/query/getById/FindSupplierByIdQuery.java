package com.kynsof.store.application.query.getById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;
import java.util.UUID;

@Getter
public class FindSupplierByIdQuery implements IQuery {
    private final UUID id;

    public FindSupplierByIdQuery(UUID id) {
        this.id = id;
    }
}

