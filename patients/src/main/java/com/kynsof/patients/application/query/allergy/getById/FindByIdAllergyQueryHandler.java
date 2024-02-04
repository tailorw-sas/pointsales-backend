package com.kynsof.patients.application.query.allergy.getById;

import com.kynsof.patients.application.query.allergy.getall.AllergyResponse;
import com.kynsof.patients.domain.bus.query.IQueryHandler;
import com.kynsof.patients.domain.dto.AllergyEntityDto;
import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.service.IAllergyService;
import com.kynsof.patients.domain.service.IContactInfoService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdAllergyQueryHandler implements IQueryHandler<FindByIdAllergyQuery, AllergyResponse>  {

    private final IAllergyService serviceImpl;

    public FindByIdAllergyQueryHandler(IAllergyService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public AllergyResponse handle(FindByIdAllergyQuery query) {
        AllergyEntityDto contactInfoDto = serviceImpl.findById(query.getId());

        return new AllergyResponse(contactInfoDto);
    }
}
