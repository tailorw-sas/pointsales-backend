package com.kynsof.store.application.query.getAll;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class GetAllOrdersQuery implements IQuery {
    private final Pageable pageable;
}

