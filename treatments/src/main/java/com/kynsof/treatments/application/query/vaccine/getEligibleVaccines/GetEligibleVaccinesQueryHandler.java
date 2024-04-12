package com.kynsof.treatments.application.query.vaccine.getEligibleVaccines;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.dto.PatientDto;
import com.kynsof.treatments.domain.service.IPatientsService;
import com.kynsof.treatments.domain.service.IVaccineService;
import org.springframework.stereotype.Component;

@Component
public class GetEligibleVaccinesQueryHandler implements IQueryHandler<GetEligibleVaccinesQuery, PaginatedResponse> {

    private final IVaccineService serviceImpl;
    private final IPatientsService patientsService;

    public GetEligibleVaccinesQueryHandler(IVaccineService serviceImpl, IPatientsService patientsService) {
        this.serviceImpl = serviceImpl;
        this.patientsService = patientsService;
    }

    @Override
    public PaginatedResponse handle(GetEligibleVaccinesQuery query) {
        PatientDto patientDto = patientsService.findById(query.getPatientId());

        return this.serviceImpl.getApplicableVaccines(patientDto.getBirthDate(), query.getPatientId(), query.getPageable());
    }
}
