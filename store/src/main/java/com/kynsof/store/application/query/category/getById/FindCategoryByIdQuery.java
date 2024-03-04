package com.kynsof.store.application.query.category.getById;


import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindCategoryByIdQuery implements IQuery {
    private final UUID id;

    public FindCategoryByIdQuery(UUID id) {
        this.id = id;
    }
}
