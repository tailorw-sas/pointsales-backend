package com.kynsof.patients.application.query.additionalInfo.getall;

import com.kynsof.patients.domain.bus.query.IQueryHandler;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.domain.service.IAdditionalInfoService;
import com.kynsof.patients.domain.service.IContactInfoService;
import org.springframework.stereotype.Component;

@Component
public class GetAllAdditionalInfoQueryHandler implements IQueryHandler<GetAllAdditionalInfoQuery, PaginatedResponse>{

    private final IAdditionalInfoService serviceImpl;

    public GetAllAdditionalInfoQueryHandler(IAdditionalInfoService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetAllAdditionalInfoQuery query) {

        return this.serviceImpl.findAll(query.getPageable(), query.getIdPatients(), query.getEmergencyContactName());
    }
}
