package com.kynsoft.rrhh.application.query.users.getbyid;

import com.kynsoft.rrhh.domain.dto.UserSystemDto;
import com.kynsoft.rrhh.domain.interfaces.services.IUserSystemService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindByIdUserSystemsQueryHandler implements IQueryHandler<FindByIdUserSystemsQuery, UserSystemsByIdResponse>  {

    private final IUserSystemService serviceImpl;

    public FindByIdUserSystemsQueryHandler(IUserSystemService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public UserSystemsByIdResponse handle(FindByIdUserSystemsQuery query) {
        UserSystemDto userSystemDto = serviceImpl.findById(query.getId());

        return new UserSystemsByIdResponse(userSystemDto);
    }
}
