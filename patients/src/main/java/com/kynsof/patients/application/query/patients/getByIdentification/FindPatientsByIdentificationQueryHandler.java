package com.kynsof.patients.application.query.patients.getByIdentification;

import com.kynsof.patients.application.query.patients.getall.PatientsResponse;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindPatientsByIdentificationQueryHandler implements IQueryHandler<FindPatientsByIdentificationQuery, PatientsResponse>  {

    private final IPatientsService serviceImpl;

    public FindPatientsByIdentificationQueryHandler(IPatientsService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PatientsResponse handle(FindPatientsByIdentificationQuery query) {
        PatientDto patient = serviceImpl.findByIdentification(query.getIdentification());

        return new PatientsResponse(patient);
    }
}
