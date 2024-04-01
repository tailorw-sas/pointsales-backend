package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.domain.dto.me.BusinessRolesPermissionsDto;
import com.kynsof.identity.domain.dto.me.PermissionInfo;
import com.kynsof.identity.domain.dto.me.RolePermissionsDto;
import com.kynsof.identity.domain.dto.me.UserMeDto;
import com.kynsof.identity.domain.interfaces.service.IUserMeService;
import com.kynsof.identity.infrastructure.identity.Business;
import com.kynsof.identity.infrastructure.identity.Permission;
import com.kynsof.identity.infrastructure.identity.RoleSystem;
import com.kynsof.identity.infrastructure.identity.UserSystem;
import com.kynsof.identity.infrastructure.repository.query.BusinessReadDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.PermissionReadDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.RolReadDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.UserSystemReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserMeServiceImpl implements IUserMeService {

    @Autowired
    private PermissionReadDataJPARepository permissionReadDataJPARepository;
    @Autowired
    private UserSystemReadDataJPARepository userSystemReadDataJPARepository;

    @Autowired
    private RolReadDataJPARepository rolReadDataJPARepository;

    @Autowired
    private BusinessReadDataJPARepository businessReadDataJPARepository;

    @Override
    public UserMeDto getUserInfo(UUID userId) {
        UserMeDto userMeDto = new UserMeDto();
        Optional<UserSystem> userSystem =  userSystemReadDataJPARepository.findById(userId);
        if (userSystem.isEmpty()) {
            throw new RuntimeException("User not found.");
        }

        userMeDto.setUserId(userId);
        userMeDto.setUserName(userSystem.get().getUserName());
        userMeDto.setEmail(userSystem.get().getEmail());
        userMeDto.setName(userSystem.get().getName());
        userMeDto.setLastName(userSystem.get().getLastName());

        List<BusinessRolesPermissionsDto> businessInfoList = new ArrayList<>();

        List<Business> businesses = businessReadDataJPARepository.findBusinessesByUserId(userId);
        for (Business business : businesses) {
            BusinessRolesPermissionsDto businessInfo = new BusinessRolesPermissionsDto();
            businessInfo.setBusinessId(business.getId());
            businessInfo.setName(business.getName());

            Set<PermissionInfo> uniquePermissions = new HashSet<>();
            List<RolePermissionsDto> roleInfoList = new ArrayList<>();

            List<RoleSystem> roles = rolReadDataJPARepository.findRolesByUserIdAndBusinessId(userId, business.getId());
            for (RoleSystem role : roles) {
                List<Permission> permissions = permissionReadDataJPARepository.findPermissionsByRoleId(role.getId());

                List<PermissionInfo> permissionsInfo = permissions.stream()
                        .map(permission -> {
                            PermissionInfo pInfo = new PermissionInfo(permission.getId(), permission.getModule().toAggregate(), permission.getCode(), permission.getDescription());
                            uniquePermissions.add(pInfo);
                            return pInfo;
                        }).collect(Collectors.toList());

                RolePermissionsDto roleInfo = new RolePermissionsDto(role.getId(), role.getName(), permissionsInfo);
                roleInfoList.add(roleInfo);
            }

            businessInfo.setRoles(roleInfoList);
            businessInfo.setUniquePermissions(uniquePermissions);
            businessInfoList.add(businessInfo);
        }

        userMeDto.setBusinesses(businessInfoList);

        return userMeDto;
    }
}
