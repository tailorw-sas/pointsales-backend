package com.kynsof.identity.domain.interfaces.service;

import com.kynsof.identity.infrastructure.identity.UserTypePermission;
import com.kynsof.share.core.domain.EUserType;

import java.util.List;

public interface IUserTypePermissionService {
    List<UserTypePermission> getPermissionsByUserType(EUserType userType);
}
