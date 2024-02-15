package com.kynsof.patients.application.query.contactInfo.getById;

import com.kynsof.patients.application.query.contactInfo.getall.ContactInfoResponse;
import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.service.IContactInfoService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindByIdContactInfoQueryHandler implements IQueryHandler<FindByIdContactInfoQuery, ContactInfoResponse>  {

    private final IContactInfoService serviceImpl;

    public FindByIdContactInfoQueryHandler(IContactInfoService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public ContactInfoResponse handle(FindByIdContactInfoQuery query) {
        ContactInfoDto contactInfoDto = serviceImpl.findById(query.getId());

        return new ContactInfoResponse(contactInfoDto);
    }
}
