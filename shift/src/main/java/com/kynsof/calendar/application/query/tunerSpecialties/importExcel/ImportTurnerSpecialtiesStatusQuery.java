package com.kynsof.calendar.application.query.tunerSpecialties.importExcel;

import com.kynsof.share.core.domain.bus.query.IQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import lombok.Getter;

@Getter
public class ImportTurnerSpecialtiesStatusQuery implements IQuery {
    private final String importProcessId;

    public ImportTurnerSpecialtiesStatusQuery(String importProcessId) {
        this.importProcessId = importProcessId;
    }
}
