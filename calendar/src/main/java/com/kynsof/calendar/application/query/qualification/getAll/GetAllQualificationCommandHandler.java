package com.kynsof.calendar.application.query.qualification.getAll;

import com.kynsof.calendar.domain.service.IQualificationService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class GetAllQualificationCommandHandler implements IQueryHandler<FindQualificationWithFilterQuery, PaginatedResponse>{

    private final IQualificationService service;

    public GetAllQualificationCommandHandler(IQualificationService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(FindQualificationWithFilterQuery query) {

        return this.service.findAll(query.getPageable(), query.getIdQualification(), query.getFilter());
    }
}
