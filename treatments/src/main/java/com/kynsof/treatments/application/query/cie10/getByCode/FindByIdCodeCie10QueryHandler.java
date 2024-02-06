package com.kynsof.treatments.application.query.cie10.getByCode;


import com.kynsof.treatments.application.query.cie10.getAll.Cie10Response;
import com.kynsof.treatments.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.domain.dto.Cie10Dto;
import com.kynsof.treatments.domain.service.ICie10Service;
import org.springframework.stereotype.Component;

@Component
public class FindByIdCodeCie10QueryHandler implements IQueryHandler<FindByCodeCie10Query, Cie10Response> {

    private final ICie10Service serviceImpl;

    public FindByIdCodeCie10QueryHandler(ICie10Service serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public Cie10Response handle(FindByCodeCie10Query query) {
        Cie10Dto contactInfoDto = serviceImpl.findByCode(query.getCode());

        return new Cie10Response(contactInfoDto);
    }
}
