package com.kynsof.identity.domain.rules;

import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.interfaces.service.IRoleService;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;

public class RolIdAndNameMustBeUniqueRule extends BusinessRule {

    private final IRoleService service;

    private RoleDto role;

    public RolIdAndNameMustBeUniqueRule(IRoleService service, RoleDto role) {
        super(
                DomainErrorMessage.COLUMN_UNIQUE, 
                new ErrorField("Rol", 
                        "The role with the name: " + role.getName() + " already exists.")
        );
        this.service = service;
        this.role = role;
    }

    @Override
    public boolean isBroken() {
        Long cant = service.countByIdAndName(role.getId(), role.getName());
        return cant > 0;
    }

}
