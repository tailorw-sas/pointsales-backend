package com.kynsof.treatments.application.query.exam.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.service.IExamService;
import org.springframework.stereotype.Component;

@Component
public class GetSearchExamQueryHandler implements IQueryHandler<GetSearchExamQuery, PaginatedResponse>{

    private final IExamService serviceImpl;

    public GetSearchExamQueryHandler(IExamService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetSearchExamQuery query) {

        return this.serviceImpl.search(query.getPageable(),query.getFilter());
    }
}
