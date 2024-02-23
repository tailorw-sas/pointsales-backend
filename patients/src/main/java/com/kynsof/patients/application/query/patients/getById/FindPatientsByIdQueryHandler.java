package com.kynsof.patients.application.query.patients.getById;

import com.kynsof.patients.application.query.patients.getall.PatientsResponse;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindPatientsByIdQueryHandler implements IQueryHandler<FindPatientsByIdQuery, PatientsResponse>  {

    private final IPatientsService serviceImpl;

    public FindPatientsByIdQueryHandler(IPatientsService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PatientsResponse handle(FindPatientsByIdQuery query) {
        PatientDto patient = serviceImpl.findById(query.getId());

        return new PatientsResponse(patient);
    }
}
