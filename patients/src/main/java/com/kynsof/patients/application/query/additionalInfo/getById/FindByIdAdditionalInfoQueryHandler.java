package com.kynsof.patients.application.query.additionalInfo.getById;

import com.kynsof.patients.application.query.additionalInfo.getall.AdditionalInfoResponse;
import com.kynsof.patients.domain.bus.query.IQueryHandler;
import com.kynsof.patients.domain.dto.AdditionalInformationDto;
import com.kynsof.patients.domain.service.IAdditionalInfoService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdAdditionalInfoQueryHandler implements IQueryHandler<FindByIdAdditionalInfoQuery, AdditionalInfoResponse>  {

    private final IAdditionalInfoService serviceImpl;

    public FindByIdAdditionalInfoQueryHandler(IAdditionalInfoService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public AdditionalInfoResponse handle(FindByIdAdditionalInfoQuery query) {
        AdditionalInformationDto additionalInformationDto = serviceImpl.findById(query.getId());

        return new AdditionalInfoResponse(additionalInformationDto);
    }
}
