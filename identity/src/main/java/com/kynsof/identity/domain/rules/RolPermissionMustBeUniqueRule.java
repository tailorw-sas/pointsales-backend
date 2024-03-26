package com.kynsof.identity.domain.rules;

import com.kynsof.identity.domain.dto.RolePermissionDto;
import com.kynsof.identity.domain.interfaces.service.IRolePermissionService;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.rules.BusinessRule;

public class RolPermissionMustBeUniqueRule extends BusinessRule {

    private final IRolePermissionService service;

    private RolePermissionDto rolePermission;

    public RolPermissionMustBeUniqueRule(IRolePermissionService service, RolePermissionDto rolePermission) {
        super(DomainErrorMessage.QUALIFICATION_DESCRIPTION_UNIQUE, "Ya existe una relacion entre el rol y el permiso!");
        this.service = service;
        this.rolePermission = rolePermission;
    }

    @Override
    public boolean isBroken() {
        Long cant = service.countByPermissionAndDeletedFalse(rolePermission);
        return cant > 0;
    }

}
