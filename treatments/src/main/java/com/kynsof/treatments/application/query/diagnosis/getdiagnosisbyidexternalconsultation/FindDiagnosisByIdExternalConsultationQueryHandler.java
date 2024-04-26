package com.kynsof.treatments.application.query.diagnosis.getdiagnosisbyidexternalconsultation;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.service.IDiagnosisService;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import org.springframework.stereotype.Component;

@Component
public class FindDiagnosisByIdExternalConsultationQueryHandler implements IQueryHandler<FindDiagnosisByIdExternalConsultationQuery, PaginatedResponse> {

    private final IDiagnosisService serviceImpl;
    private final IExternalConsultationService externalConsultationService;

    public FindDiagnosisByIdExternalConsultationQueryHandler(IDiagnosisService serviceImpl, IExternalConsultationService externalConsultationService) {
        this.serviceImpl = serviceImpl;
        this.externalConsultationService = externalConsultationService;
    }

    @Override
    public PaginatedResponse handle(FindDiagnosisByIdExternalConsultationQuery query) {
        ExternalConsultationDto externalConsultationDto = this.externalConsultationService.findById(query.getId());
        return this.serviceImpl.findByExternalConsultation(externalConsultationDto, query.getPageable());
    }
}
