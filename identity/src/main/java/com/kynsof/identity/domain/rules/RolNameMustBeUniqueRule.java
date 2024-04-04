package com.kynsof.identity.domain.rules;

import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.interfaces.service.IRoleService;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import org.springframework.beans.factory.annotation.Autowired;

public class RolNameMustBeUniqueRule extends BusinessRule {

    @Autowired
    private IRoleService service;

    private RoleDto role;

    public RolNameMustBeUniqueRule(RoleDto role) {
        super(
                DomainErrorMessage.COLUMN_UNIQUE, 
                new ErrorField("Rol", 
                        "The role with the name: " + role.getName() + " already exists.")
        );
        this.role = role;
    }

    @Override
    public boolean isBroken() {
        Long cant = service.countByName(role.getName());
        return cant > 0;
    }

}
