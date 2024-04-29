package com.kynsof.treatments.application.query.examOrder.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.service.IExamOrderService;
import org.springframework.stereotype.Component;

@Component
public class GetSearchExamOrderQueryHandler implements IQueryHandler<GetSearchExamOrderQuery, PaginatedResponse>{

    private final IExamOrderService serviceImpl;

    public GetSearchExamOrderQueryHandler(IExamOrderService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetSearchExamOrderQuery query) {

        return this.serviceImpl.search(query.getPageable(),query.getFilter());
    }
}
