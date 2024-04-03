package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.domain.dto.me.BusinessRolesPermissionsDto;
import com.kynsof.identity.domain.dto.me.PermissionInfo;
import com.kynsof.identity.domain.dto.me.RolePermissionsDto;
import com.kynsof.identity.domain.dto.me.UserMeDto;
import com.kynsof.identity.domain.interfaces.service.IUserMeService;
import com.kynsof.identity.infrastructure.identity.Business;
import com.kynsof.identity.infrastructure.identity.UserSystem;
import com.kynsof.identity.infrastructure.repository.query.BusinessReadDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.PermissionReadDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.UserSystemReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserMeServiceImpl implements IUserMeService {

    @Autowired
    private PermissionReadDataJPARepository permissionReadDataJPARepository;
    @Autowired
    private UserSystemReadDataJPARepository userSystemReadDataJPARepository;


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
            
            businessInfo.setRoles(roleInfoList);
            businessInfo.setUniquePermissions(uniquePermissions);
            businessInfoList.add(businessInfo);
        }

        userMeDto.setBusinesses(businessInfoList);

        return userMeDto;
    }
}
