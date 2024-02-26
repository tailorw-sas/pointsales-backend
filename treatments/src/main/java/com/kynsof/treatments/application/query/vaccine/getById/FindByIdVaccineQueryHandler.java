package com.kynsof.treatments.application.query.vaccine.getById;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.application.query.vaccine.getall.VaccineResponse;
import com.kynsof.treatments.domain.dto.VaccineDto;
import com.kynsof.treatments.domain.service.IVaccineService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdVaccineQueryHandler implements IQueryHandler<FindByIdVaccineQuery, VaccineResponse> {

    private final IVaccineService serviceImpl;

    public FindByIdVaccineQueryHandler(IVaccineService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public VaccineResponse handle(FindByIdVaccineQuery query) {
        VaccineDto contactInfoDto = serviceImpl.findById(query.getId());

        return new VaccineResponse(contactInfoDto);
    }
}
