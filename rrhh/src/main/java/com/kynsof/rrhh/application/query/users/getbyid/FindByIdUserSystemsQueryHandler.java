package com.kynsof.rrhh.application.query.users.getbyid;

import com.kynsof.rrhh.doman.dto.UserSystemDto;
import com.kynsof.rrhh.doman.interfaces.services.IUserSystemService;
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
