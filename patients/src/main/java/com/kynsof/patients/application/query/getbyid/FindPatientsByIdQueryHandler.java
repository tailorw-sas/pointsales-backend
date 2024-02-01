package com.kynsof.patients.application.query.getById;

import com.kynsof.patients.application.query.getall.PatientsResponse;
import com.kynsof.patients.domain.PatientDto;
import com.kynsof.patients.domain.bus.query.IQueryHandler;
import com.kynsof.patients.infrastructure.services.PatientsServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class FindPatientsByIdQueryHandler implements IQueryHandler<FindPatientsByIdQuery, PatientsResponse>  {

    private final PatientsServiceImpl serviceImpl;

    public FindPatientsByIdQueryHandler(PatientsServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PatientsResponse handle(FindPatientsByIdQuery query) {
        PatientDto patient = serviceImpl.findById(query.getId());

        return new PatientsResponse(patient);
    }
}
