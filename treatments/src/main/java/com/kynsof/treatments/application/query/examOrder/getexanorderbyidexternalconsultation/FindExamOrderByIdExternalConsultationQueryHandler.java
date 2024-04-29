package com.kynsof.treatments.application.query.examOrder.getexanorderbyidexternalconsultation;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.application.query.examOrder.getall.ExamOrderResponse;
import com.kynsof.treatments.domain.dto.ExamOrderDto;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.service.IExamOrderService;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import org.springframework.stereotype.Component;

@Component
public class FindExamOrderByIdExternalConsultationQueryHandler implements IQueryHandler<FindExamOrderByIdExternalConsultationQuery, ExamOrderResponse> {

    private final IExternalConsultationService externalConsultationService;
    private final IExamOrderService examOrderService;

    public FindExamOrderByIdExternalConsultationQueryHandler(IExamOrderService examOrderService,
                                                             IExternalConsultationService externalConsultationService) {
        this.examOrderService = examOrderService;
        this.externalConsultationService = externalConsultationService;
    }

    @Override
    public ExamOrderResponse handle(FindExamOrderByIdExternalConsultationQuery query) {
        ExternalConsultationDto externalConsultationDto = this.externalConsultationService.findById(query.getId());
        ExamOrderDto examOrderDto = this.examOrderService.findById(externalConsultationDto.getId());

        return new ExamOrderResponse(examOrderDto);
    }
}
