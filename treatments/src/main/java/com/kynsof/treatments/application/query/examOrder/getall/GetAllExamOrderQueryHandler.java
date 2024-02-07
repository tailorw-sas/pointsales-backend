package com.kynsof.treatments.application.query.examOrder.getall;

import com.kynsof.treatments.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.domain.dto.PaginatedResponse;
import com.kynsof.treatments.domain.service.IExamOrderService;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import org.springframework.stereotype.Component;

@Component
public class GetAllExamOrderQueryHandler implements IQueryHandler<GetAllExamOrderQuery, PaginatedResponse> {

    private final IExamOrderService serviceImpl;

    public GetAllExamOrderQueryHandler(IExamOrderService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetAllExamOrderQuery query) {

        return this.serviceImpl.findAll(query.getPageable(),  query.getPatientId());
    }
}