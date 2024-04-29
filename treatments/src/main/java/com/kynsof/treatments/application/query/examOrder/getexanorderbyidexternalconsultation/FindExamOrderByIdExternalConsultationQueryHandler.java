package com.kynsof.treatments.application.query.examOrder.getexanorderbyidexternalconsultation;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.application.query.examOrder.getall.ExamOrderResponse;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import org.springframework.stereotype.Component;

@Component
public class FindExamOrderByIdExternalConsultationQueryHandler implements IQueryHandler<FindExamOrderByIdExternalConsultationQuery, ExamOrderResponse> {

    private final IExternalConsultationService externalConsultationService;

    public FindExamOrderByIdExternalConsultationQueryHandler(IExternalConsultationService externalConsultationService) {
        this.externalConsultationService = externalConsultationService;
    }

    @Override
    public ExamOrderResponse handle(FindExamOrderByIdExternalConsultationQuery query) {
        ExternalConsultationDto externalConsultationDt = this.externalConsultationService.findById(query.getId());

        return new ExamOrderResponse(externalConsultationDt.getExamOrder());
    }
}
