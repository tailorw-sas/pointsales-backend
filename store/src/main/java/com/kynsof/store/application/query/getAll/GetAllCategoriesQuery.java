package com.kynsof.store.application.query.getAll;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
public class GetAllCategoriesQuery implements IQuery {
    private Pageable pageable;
}
