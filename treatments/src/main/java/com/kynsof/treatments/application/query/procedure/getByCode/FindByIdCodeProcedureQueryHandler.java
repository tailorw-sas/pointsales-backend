package com.kynsof.treatments.application.query.procedure.getByCode;


import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.application.query.procedure.getAll.ProcedureResponse;
import com.kynsof.treatments.domain.dto.ProcedureDto;
import com.kynsof.treatments.domain.service.IProcedureService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdCodeProcedureQueryHandler implements IQueryHandler<FindByCodeProcedureQuery, ProcedureResponse> {

    private final IProcedureService serviceImpl;

    public FindByIdCodeProcedureQueryHandler(IProcedureService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public ProcedureResponse handle(FindByCodeProcedureQuery query) {
        ProcedureDto contactInfoDto = serviceImpl.findByCode(query.getCode());

        return new ProcedureResponse(contactInfoDto);
    }
}
