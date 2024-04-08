package com.kynsof.treatments.application.query.vaccine.getEligibleVaccines;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.application.query.vaccine.getall.VaccineResponse;
import com.kynsof.treatments.domain.dto.PatientDto;
import com.kynsof.treatments.domain.dto.VaccineDto;
import com.kynsof.treatments.domain.service.IPatientsService;
import com.kynsof.treatments.domain.service.IVaccineService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetEligibleVaccinesQueryHandler implements IQueryHandler<GetEligibleVaccinesQuery,  EligibleVaccinesResponse> {

    private final IVaccineService serviceImpl;
    private final IPatientsService patientsService;

    public GetEligibleVaccinesQueryHandler(IVaccineService serviceImpl, IPatientsService patientsService) {
        this.serviceImpl = serviceImpl;
        this.patientsService = patientsService;
    }

    @Override
    public EligibleVaccinesResponse handle(GetEligibleVaccinesQuery query) {
        PatientDto patientDto = patientsService.findById(query.getPatientId());
        List<VaccineDto> vaccineResponses = this.serviceImpl.getApplicableVaccines(patientDto.getBirthDate(),query.getPatientId());
        EligibleVaccinesResponse response = new EligibleVaccinesResponse();

        List<VaccineResponse> responses = new ArrayList<>();
        for (VaccineDto vaccineDto : vaccineResponses) {
            VaccineResponse vaccineResponse = new VaccineResponse(vaccineDto);
            responses.add(vaccineResponse);
        }
        response.setVaccineResponses(responses);

        return response;
    }
}
