package com.kynsof.treatments.application.query.treatment.getdiagnosisbyidexternalconsultation;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import com.kynsof.treatments.domain.service.ITreatmentService;
import org.springframework.stereotype.Component;

@Component
public class FindTreatmentByIdExternalConsultationQueryHandler implements IQueryHandler<FindTreatmentByIdExternalConsultationQuery, PaginatedResponse> {

    private final ITreatmentService serviceImpl;
    private final IExternalConsultationService externalConsultationService;

    public FindTreatmentByIdExternalConsultationQueryHandler(ITreatmentService serviceImpl, IExternalConsultationService externalConsultationService) {
        this.serviceImpl = serviceImpl;
        this.externalConsultationService = externalConsultationService;
    }

    @Override
    public PaginatedResponse handle(FindTreatmentByIdExternalConsultationQuery query) {
        ExternalConsultationDto externalConsultationDto = this.externalConsultationService.findById(query.getId());
        return this.serviceImpl.findByExternalConsultation(externalConsultationDto, query.getPageable());
    }
}
