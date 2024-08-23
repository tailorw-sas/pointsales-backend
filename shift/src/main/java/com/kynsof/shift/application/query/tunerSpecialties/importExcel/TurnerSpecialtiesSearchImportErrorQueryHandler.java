package com.kynsof.shift.application.query.tunerSpecialties.importExcel;

import com.kynsof.share.core.domain.bus.query.IQuery;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.request.PageableUtil;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.shift.infrastructure.service.ImportTurnerSpecialtiesServiceImpl;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class TurnerSpecialtiesSearchImportErrorQueryHandler implements
        IQueryHandler<TurnerSpecialtiesSearchImportErrorQuery,PaginatedResponse> {

    private final ImportTurnerSpecialtiesServiceImpl importTurnerSpecialtiesService;

    public TurnerSpecialtiesSearchImportErrorQueryHandler(ImportTurnerSpecialtiesServiceImpl importTurnerSpecialtiesService) {
        this.importTurnerSpecialtiesService = importTurnerSpecialtiesService;
    }


    @Override
    public PaginatedResponse handle(TurnerSpecialtiesSearchImportErrorQuery query) {
        Pageable pageable =PageableUtil.createPageable(query.getSearchRequest());
        String importProcessId=query.getSearchRequest().getQuery();
        return importTurnerSpecialtiesService.getTurnerSpecialtiesImportError(importProcessId,pageable);
    }
}
