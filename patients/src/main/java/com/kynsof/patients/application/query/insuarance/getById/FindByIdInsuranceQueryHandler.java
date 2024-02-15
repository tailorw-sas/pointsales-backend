package com.kynsof.patients.application.query.insuarance.getById;

import com.kynsof.patients.application.query.insuarance.getall.InsuranceResponse;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.patients.domain.dto.InsuranceDto;
import com.kynsof.patients.domain.service.IInsuranceService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdInsuranceQueryHandler implements IQueryHandler<FindByIdInsuranceQuery, InsuranceResponse>  {

    private final IInsuranceService serviceImpl;

    public FindByIdInsuranceQueryHandler(IInsuranceService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public InsuranceResponse handle(FindByIdInsuranceQuery query) {
        InsuranceDto insuranceDto = serviceImpl.findById(query.getId());

        return new InsuranceResponse(insuranceDto);
    }
}
