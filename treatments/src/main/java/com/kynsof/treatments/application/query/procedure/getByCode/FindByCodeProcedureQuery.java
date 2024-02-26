package com.kynsof.treatments.application.query.procedure.getByCode;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

@Getter
public class FindByCodeProcedureQuery implements IQuery {

    private final String code;

    public FindByCodeProcedureQuery(String code) {
        this.code = code;
    }

}
