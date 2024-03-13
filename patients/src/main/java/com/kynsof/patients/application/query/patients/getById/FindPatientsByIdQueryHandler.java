package com.kynsof.patients.application.query.patients.getById;

import com.kynsof.patients.domain.dto.PatientByIdDto;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindPatientsByIdQueryHandler implements IQueryHandler<FindPatientsByIdQuery, PatientByIdResponse>  {

    private final IPatientsService serviceImpl;

    public FindPatientsByIdQueryHandler(IPatientsService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PatientByIdResponse handle(FindPatientsByIdQuery query) {
        PatientByIdDto patient = serviceImpl.findById(query.getId());
        return new PatientByIdResponse(patient);
    }
}
