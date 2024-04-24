package com.kynsof.patients.application.query.contactInfo.getcontactinfobyidpatient;

import com.kynsof.patients.application.query.contactInfo.getall.ContactInfoResponse;
import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.service.IContactInfoService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindContactInfoByIdPatientQueryHandler implements IQueryHandler<FindContactInfoByIdPatientQuery, ContactInfoResponse>  {

    private final IContactInfoService serviceImpl;

    public FindContactInfoByIdPatientQueryHandler(IContactInfoService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public ContactInfoResponse handle(FindContactInfoByIdPatientQuery query) {
        ContactInfoDto contactInfoDto = serviceImpl.findByPatientId(query.getId());

        return new ContactInfoResponse(contactInfoDto);
    }
}
