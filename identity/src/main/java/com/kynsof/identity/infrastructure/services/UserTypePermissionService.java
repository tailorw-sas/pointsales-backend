package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.domain.interfaces.service.IUserTypePermissionService;
import com.kynsof.identity.infrastructure.identity.UserTypePermission;
import com.kynsof.identity.infrastructure.repository.query.UserTypePermissionReadDataJPARepository;
import com.kynsof.share.core.domain.EUserType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserTypePermissionService implements IUserTypePermissionService {
    @Autowired
    private UserTypePermissionReadDataJPARepository userTypePermissionRepository;
    @Override
    public List<UserTypePermission> getPermissionsByUserType(EUserType userType) {
        return userTypePermissionRepository.findByUserType(userType);
    }
}
