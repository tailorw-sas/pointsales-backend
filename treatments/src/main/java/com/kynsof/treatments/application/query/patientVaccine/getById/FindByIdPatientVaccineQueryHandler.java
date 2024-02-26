package com.kynsof.treatments.application.query.patientVaccine.getById;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.application.query.patientVaccine.getall.PatientVaccineResponse;
import com.kynsof.treatments.domain.dto.PatientVaccineDto;
import com.kynsof.treatments.domain.service.IPatientVaccineService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdPatientVaccineQueryHandler implements IQueryHandler<FindByIdPatientVaccineQuery, PatientVaccineResponse> {

    private final IPatientVaccineService serviceImpl;

    public FindByIdPatientVaccineQueryHandler(IPatientVaccineService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PatientVaccineResponse handle(FindByIdPatientVaccineQuery query) {
        PatientVaccineDto contactInfoDto = serviceImpl.findById(query.getId());

        return new PatientVaccineResponse(contactInfoDto);
    }
}
