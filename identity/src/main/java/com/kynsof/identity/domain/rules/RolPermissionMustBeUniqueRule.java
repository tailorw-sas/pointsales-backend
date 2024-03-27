package com.kynsof.identity.domain.rules;

import com.kynsof.identity.domain.dto.RolePermissionDto;
import com.kynsof.identity.domain.interfaces.service.IRolePermissionService;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;

public class RolPermissionMustBeUniqueRule extends BusinessRule {

    private final IRolePermissionService service;

    private RolePermissionDto rolePermission;

    public RolPermissionMustBeUniqueRule(IRolePermissionService service, RolePermissionDto rolePermission) {
        super(
                DomainErrorMessage.RELATIONSHIP_MUST_BE_UNIQUE, 
                new ErrorField("Permission", 
                        "The permission code: " + 
                                rolePermission.getPermission().getCode() + 
                                " is already related to the role " + rolePermission.getRole().getName())
        );
        this.service = service;
        this.rolePermission = rolePermission;
    }

    @Override
    public boolean isBroken() {
        Long cant = service.countByPermissionAndDeletedFalse(rolePermission);
        return cant > 0;
    }

}
