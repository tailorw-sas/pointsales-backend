package com.kynsof.patients.application.query.getbyid;

import com.kynsof.patients.application.query.getall.PatientsResponse;
import com.kynsof.patients.domain.Patients;
import com.kynsof.patients.domain.bus.query.IQueryHandler;
import com.kynsof.patients.infrastructure.service.PatientsServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class FindPatientsByIdQueryHandler implements IQueryHandler<FindPatientsByIdQuery, PatientsResponse>  {

    private final PatientsServiceImpl serviceImpl;

    public FindPatientsByIdQueryHandler(PatientsServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PatientsResponse handle(FindPatientsByIdQuery query) {

        Patients patient = serviceImpl.findById(query.getId());

        return new PatientsResponse(patient);
    }

}
