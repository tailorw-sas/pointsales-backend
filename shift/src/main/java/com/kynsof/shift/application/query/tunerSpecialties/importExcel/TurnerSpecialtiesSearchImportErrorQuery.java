package com.kynsof.shift.application.query.tunerSpecialties.importExcel;

import com.kynsof.share.core.domain.bus.query.IQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import lombok.Getter;

@Getter
public class TurnerSpecialtiesSearchImportErrorQuery implements IQuery {
    private final SearchRequest searchRequest;
    public TurnerSpecialtiesSearchImportErrorQuery(SearchRequest request) {
        this.searchRequest=request;
    }
}
