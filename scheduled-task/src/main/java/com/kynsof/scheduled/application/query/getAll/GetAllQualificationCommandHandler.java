package com.kynsof.scheduled.application.query.getAll;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQueryHandler;
import com.kynsof.scheduled.infrastructure.service.QualificationServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class GetAllQualificationCommandHandler implements IQueryHandler<FindQualificationWithFilterQuery, PaginatedResponse>{

    private final QualificationServiceImpl serviceImpl;

    public GetAllQualificationCommandHandler(QualificationServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(FindQualificationWithFilterQuery query) {

        return this.serviceImpl.findAll(query.getPageable(), query.getIdQualification(), query.getFilter());
    }
}
