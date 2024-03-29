package com.kynsof.identity.application.query.userrolbusiness.getbyid;

import com.kynsof.identity.domain.dto.UserRoleBusinessDto;
import com.kynsof.identity.domain.interfaces.service.IUserRoleBusinessService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindByIdUserRoleBusinessQueryHandler implements IQueryHandler<FindByIdUserRoleBusinessQuery, UserRoleBusinessResponse>  {

    private final IUserRoleBusinessService serviceImpl;

    public FindByIdUserRoleBusinessQueryHandler(IUserRoleBusinessService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public UserRoleBusinessResponse handle(FindByIdUserRoleBusinessQuery query) {
        UserRoleBusinessDto userRoleBusinessDto = serviceImpl.findById(query.getId());

        return new UserRoleBusinessResponse(userRoleBusinessDto);
    }
}
