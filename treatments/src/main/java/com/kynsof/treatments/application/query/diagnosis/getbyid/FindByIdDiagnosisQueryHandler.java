package com.kynsof.treatments.application.query.diagnosis.getbyid;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.domain.dto.DiagnosisDto;
import com.kynsof.treatments.domain.service.IDiagnosisService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdDiagnosisQueryHandler implements IQueryHandler<FindByIdDiagnosisQuery, DiagnosisResponse> {

    private final IDiagnosisService serviceImpl;

    public FindByIdDiagnosisQueryHandler(IDiagnosisService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public DiagnosisResponse handle(FindByIdDiagnosisQuery query) {
        DiagnosisDto object = serviceImpl.findById(query.getId());

        return new DiagnosisResponse(object);
    }
}
