package com.kynsof.identity.domain.rules;

import com.kynsof.identity.domain.dto.UserRoleBusinessDto;
import com.kynsof.identity.domain.interfaces.service.IUserRoleBusinessService;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;

public class UserRoleBusinessMustBeUniqueRule extends BusinessRule {

    private final IUserRoleBusinessService service;

    private UserRoleBusinessDto userRoleBusinessDto;

    public UserRoleBusinessMustBeUniqueRule(IUserRoleBusinessService service, UserRoleBusinessDto userRoleBusinessDto) {
        super(
                DomainErrorMessage.RELATIONSHIP_MUST_BE_UNIQUE, 
                new ErrorField("UserRoleBusiness", 
                        "The role: " + 
                                userRoleBusinessDto.getRole().getName() + " for user: " + userRoleBusinessDto.getUser().getUserName() +
                                " is already related to the business " + userRoleBusinessDto.getBusiness().getName())
        );
        this.service = service;
        this.userRoleBusinessDto = userRoleBusinessDto;
    }

    @Override
    public boolean isBroken() {
        Long cant = service.countByUserIdAndRoleIdAndBusinessIdAndDeletedFalse(userRoleBusinessDto);
        return cant > 0;
    }

}
