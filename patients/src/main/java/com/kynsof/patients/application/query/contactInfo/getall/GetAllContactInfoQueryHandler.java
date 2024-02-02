package com.kynsof.patients.application.query.contactInfo.getall;

import com.kynsof.patients.domain.bus.query.IQueryHandler;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.domain.service.IContactInfoService;
import org.springframework.stereotype.Component;

@Component
public class GetAllContactInfoQueryHandler implements IQueryHandler<GetAllContactInfoQuery, PaginatedResponse>{

    private final IContactInfoService serviceImpl;

    public GetAllContactInfoQueryHandler(IContactInfoService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetAllContactInfoQuery query) {

        return this.serviceImpl.findAll(query.getPageable(), query.getIdPatients(), query.getEmail(), query.getPhone());
    }
}
