package com.kynsof.treatments.application.query.optometryExam.getById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdOptometryExamQuery implements IQuery {

    private final UUID id;

    public FindByIdOptometryExamQuery(UUID id) {
        this.id = id;
    }

}
