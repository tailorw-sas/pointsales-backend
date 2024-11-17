package com.kynsof.treatments.application.query.businessProcedure.getbyid;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.application.query.businessProcedure.search.BusinessProcedureResponse;
import com.kynsof.treatments.domain.dto.BusinessProcedureDto;
import com.kynsof.treatments.domain.service.IBusinessProcedureService;
import org.springframework.stereotype.Component;

@Component
public class FindBusinessProcedureByIdQueryHandler implements IQueryHandler<FindBusinessProcedureByIdQuery, BusinessProcedureResponse>  {

    private final IBusinessProcedureService service;

    public FindBusinessProcedureByIdQueryHandler(IBusinessProcedureService service) {
        this.service = service;
    }

    @Override
    public BusinessProcedureResponse handle(FindBusinessProcedureByIdQuery query) {
        BusinessProcedureDto response = service.findById(query.getId());

        return new BusinessProcedureResponse(response);
    }
}
