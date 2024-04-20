package com.kynsof.treatments.application.query.exam.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdExamQuery implements IQuery {

    private final UUID id;

    public FindByIdExamQuery(UUID id) {
        this.id = id;
    }

}
