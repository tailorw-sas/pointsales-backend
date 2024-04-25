package com.kynsof.treatments.application.query.externalConsultation.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import org.springframework.stereotype.Component;

@Component
public class GetSearchExternalConsultationQueryHandler implements IQueryHandler<GetSearchExternalConsultationQuery, PaginatedResponse>{

    private final IExternalConsultationService serviceImpl;

    public GetSearchExternalConsultationQueryHandler(IExternalConsultationService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetSearchExternalConsultationQuery query) {

        return this.serviceImpl.search(query.getPageable(),query.getFilter());
    }
}
