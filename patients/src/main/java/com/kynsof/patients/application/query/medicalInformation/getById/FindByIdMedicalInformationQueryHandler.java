package com.kynsof.patients.application.query.medicalInformation.getById;

import com.kynsof.patients.application.query.medicalInformation.getall.MedicalInformationResponse;
import com.kynsof.patients.domain.bus.query.IQueryHandler;
import com.kynsof.patients.domain.dto.AdditionalInformationDto;
import com.kynsof.patients.domain.dto.MedicalInformationDto;
import com.kynsof.patients.domain.service.IAdditionalInfoService;
import com.kynsof.patients.domain.service.IMedicalInformationService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdMedicalInformationQueryHandler implements IQueryHandler<FindByIdMedicalInformationQuery, MedicalInformationResponse>  {

    private final IMedicalInformationService serviceImpl;

    public FindByIdMedicalInformationQueryHandler(IMedicalInformationService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public MedicalInformationResponse handle(FindByIdMedicalInformationQuery query) {
        MedicalInformationDto medicalInformationDto = serviceImpl.findById(query.getId());

        return new MedicalInformationResponse(medicalInformationDto);
    }
}
