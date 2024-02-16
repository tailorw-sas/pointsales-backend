package com.kynsof.patients.application.query.medicalInformation.getall;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.domain.service.IMedicalInformationService;
import org.springframework.stereotype.Component;

@Component
public class GetAllMedicalInformationQueryHandler implements IQueryHandler<GetAllMedicalInformationQuery, PaginatedResponse>{

    private final IMedicalInformationService serviceImpl;

    public GetAllMedicalInformationQueryHandler(IMedicalInformationService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetAllMedicalInformationQuery query) {

        return this.serviceImpl.findAll(query.getPageable());
    }
}
