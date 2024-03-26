package com.kynsof.treatments.application.query.vaccine.getEligibleVaccines;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.application.query.vaccine.getall.VaccineResponse;
import com.kynsof.treatments.domain.service.IVaccineService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetEligibleVaccinesQueryHandler implements IQueryHandler<GetEligibleVaccinesQuery,  EligibleVaccinesResponse> {

    private final IVaccineService serviceImpl;

    public GetEligibleVaccinesQueryHandler(IVaccineService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public EligibleVaccinesResponse handle(GetEligibleVaccinesQuery query) {
        List<VaccineResponse> vaccineResponses = this.serviceImpl.getEligibleVaccines(query.getAge());
        EligibleVaccinesResponse response = new EligibleVaccinesResponse();
        response.setVaccineResponses(vaccineResponses);

        return response;
    }
}
