package com.kynsof.treatments.application.query.cie10.getByCode;

import com.kynsof.treatments.domain.bus.query.IQuery;
import lombok.Getter;

@Getter
public class FindByCodeCie10Query implements IQuery {

    private final String code;

    public FindByCodeCie10Query(String code) {
        this.code = code;
    }

}
