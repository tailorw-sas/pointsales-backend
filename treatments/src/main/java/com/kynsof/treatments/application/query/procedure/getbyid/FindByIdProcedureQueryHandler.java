package com.kynsof.treatments.application.query.procedure.getbyid;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.application.query.procedure.getAll.ProcedureResponse;
import com.kynsof.treatments.domain.dto.ProcedureDto;
import com.kynsof.treatments.domain.service.IProcedureService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdProcedureQueryHandler implements IQueryHandler<FindByIdProcedureQuery, ProcedureResponse> {

    private final IProcedureService serviceImpl;

    public FindByIdProcedureQueryHandler(IProcedureService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public ProcedureResponse handle(FindByIdProcedureQuery query) {
        ProcedureDto procedureDto = serviceImpl.findById(query.getId());

        return new ProcedureResponse(procedureDto);
    }
}
