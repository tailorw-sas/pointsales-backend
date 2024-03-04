package com.kynsof.store.application.query.subcategory.getById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindSubcategoryByIdQuery implements IQuery {
    private final UUID id;

    public FindSubcategoryByIdQuery(UUID id) {
        this.id = id;
    }
}
