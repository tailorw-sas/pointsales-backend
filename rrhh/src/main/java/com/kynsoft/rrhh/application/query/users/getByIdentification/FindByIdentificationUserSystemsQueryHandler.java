package com.kynsoft.rrhh.application.query.users.getByIdentification;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsoft.rrhh.domain.dto.UserSystemDto;
import com.kynsoft.rrhh.domain.interfaces.services.IUserSystemService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdentificationUserSystemsQueryHandler implements IQueryHandler<FindByIdentificationUserSystemsQuery, UserSystemsByIdentificationResponse>  {

    private final IUserSystemService serviceImpl;

    public FindByIdentificationUserSystemsQueryHandler(IUserSystemService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public UserSystemsByIdentificationResponse handle(FindByIdentificationUserSystemsQuery query) {
        UserSystemDto userSystemDto = serviceImpl.getUserByIdentification(query.getIdentification());

        return new UserSystemsByIdentificationResponse(userSystemDto);
    }
}
