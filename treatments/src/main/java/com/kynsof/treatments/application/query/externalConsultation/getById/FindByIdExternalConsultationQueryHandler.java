package com.kynsof.treatments.application.query.externalConsultation.getById;
import com.kynsof.treatments.application.query.externalConsultation.getall.ExternalConsultationResponse;
import com.kynsof.treatments.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdExternalConsultationQueryHandler implements IQueryHandler<FindByIdExternalConsultationQuery, ExternalConsultationResponse> {

    private final IExternalConsultationService serviceImpl;

    public FindByIdExternalConsultationQueryHandler(IExternalConsultationService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public ExternalConsultationResponse handle(FindByIdExternalConsultationQuery query) {
        ExternalConsultationDto contactInfoDto = serviceImpl.findById(query.getId());

        return new ExternalConsultationResponse(contactInfoDto);
    }
}
