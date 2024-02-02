package com.kynsof.patients.application.query.medicalInformation.getall;

import com.kynsof.patients.domain.bus.query.IQueryHandler;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.domain.service.IAdditionalInfoService;
import org.springframework.stereotype.Component;

@Component
public class GetAllMedicalInformationQueryHandler implements IQueryHandler<GetMedicalInformationQuery, PaginatedResponse>{

    private final IAdditionalInfoService serviceImpl;

    public GetAllMedicalInformationQueryHandler(IAdditionalInfoService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetMedicalInformationQuery query) {

        return this.serviceImpl.findAll(query.getPageable(), query.getIdPatients(), query.getEmergencyContactName());
    }
}
