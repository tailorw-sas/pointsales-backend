package com.kynsof.treatments.application.query.examOrder.getById;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.application.query.examOrder.getall.ExamOrderResponse;
import com.kynsof.treatments.domain.dto.ExamOrderDto;
import com.kynsof.treatments.domain.service.IExamOrderService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdExamOrderQueryHandler implements IQueryHandler<FindByIdExamOrderQuery, ExamOrderResponse> {

    private final IExamOrderService serviceImpl;

    public FindByIdExamOrderQueryHandler(IExamOrderService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public ExamOrderResponse handle(FindByIdExamOrderQuery query) {
        ExamOrderDto contactInfoDto = serviceImpl.findById(query.getId());

        return new ExamOrderResponse(contactInfoDto);
    }
}
