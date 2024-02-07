package com.kynsof.treatments.application.query.examOrder.getById;

import com.kynsof.treatments.domain.bus.query.IQuery;
import lombok.Getter;
import java.util.UUID;

@Getter
public class FindByIdExamOrderQuery implements IQuery {

    private final UUID id;

    public FindByIdExamOrderQuery(UUID id) {
        this.id = id;
    }

}
