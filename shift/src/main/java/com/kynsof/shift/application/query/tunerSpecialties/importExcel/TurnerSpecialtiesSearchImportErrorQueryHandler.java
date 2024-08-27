package com.kynsof.shift.application.query.tunerSpecialties.importExcel;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.request.PageableUtil;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.shift.domain.service.ImportTurnerSpecialtiesService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class TurnerSpecialtiesSearchImportErrorQueryHandler implements
        IQueryHandler<TurnerSpecialtiesSearchImportErrorQuery,PaginatedResponse> {

    private final ImportTurnerSpecialtiesService importTurnerSpecialtiesService;

    public TurnerSpecialtiesSearchImportErrorQueryHandler(ImportTurnerSpecialtiesService importTurnerSpecialtiesService) {
        this.importTurnerSpecialtiesService = importTurnerSpecialtiesService;
    }


    @Override
    public PaginatedResponse handle(TurnerSpecialtiesSearchImportErrorQuery query) {
        Pageable pageable =PageableUtil.createPageable(query.getSearchRequest());
        String importProcessId=query.getSearchRequest().getQuery();

        return importTurnerSpecialtiesService.getTurnerSpecialtiesImportError(importProcessId,pageable);
    }
}
