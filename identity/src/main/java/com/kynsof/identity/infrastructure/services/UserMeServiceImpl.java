package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.domain.dto.ModuleDto;
import com.kynsof.identity.domain.dto.me.BusinessModulePermissionsDto;
import com.kynsof.identity.domain.dto.me.PermissionInfo;
import com.kynsof.identity.domain.dto.me.UserMeDto;
import com.kynsof.identity.domain.interfaces.service.IUserMeService;
import com.kynsof.identity.infrastructure.identity.Business;
import com.kynsof.identity.infrastructure.identity.ModuleSystem;
import com.kynsof.identity.infrastructure.identity.Permission;
import com.kynsof.identity.infrastructure.identity.UserSystem;
import com.kynsof.identity.infrastructure.repository.query.BusinessModuleReadDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.BusinessReadDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.PermissionReadDataJPARepository;
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
    private BusinessModuleReadDataJPARepository businessModuleReadDataJPARepository;

    @Autowired
    private BusinessReadDataJPARepository businessReadDataJPARepository;

    @Override
    public UserMeDto getUserInfo(UUID userId) {
        Optional<UserSystem> userOptional = userSystemReadDataJPARepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found.");
        }

        UserSystem user = userOptional.get();
        UserMeDto userMeDto = new UserMeDto();
        userMeDto.setUserId(user.getId());
        userMeDto.setUserName(user.getUserName());
        userMeDto.setEmail(user.getEmail());
        userMeDto.setName(user.getName());
        userMeDto.setLastName(user.getLastName());

        Set<BusinessModulePermissionsDto> businessModulePermissionsDtos = new HashSet<>();

        List<Business> businesses = businessReadDataJPARepository.findBusinessesByUserId(userId);
        for (Business business : businesses) {
            BusinessModulePermissionsDto businessModulePermissionsDto = new BusinessModulePermissionsDto();
            businessModulePermissionsDto.setBusinessId(business.getId());
            businessModulePermissionsDto.setName(business.getName());

            List<ModuleSystem> modules = businessModuleReadDataJPARepository.findModuleSystemByBusinessId(business.getId());
            Set<PermissionInfo> uniquePermissions = new HashSet<>();
            for (ModuleSystem module : modules) {
                List<Permission> permissions = permissionReadDataJPARepository.findByModuleIdAndBusinessId(module.getId(), business.getId());
                Set<PermissionInfo> permissionInfos = permissions.stream()
                        .map(p -> new PermissionInfo(p.getId(), new ModuleDto(p.getModule().getId(), p.getModule().getName(), p.getModule().getImage(), p.getModule().getDescription()), p.getCode(), p.getDescription()))
                        .collect(Collectors.toSet());
                uniquePermissions.addAll(permissionInfos);
            }

            businessModulePermissionsDto.setUniquePermissions(uniquePermissions);
            businessModulePermissionsDtos.add(businessModulePermissionsDto);
        }

        userMeDto.setBusiness(businessModulePermissionsDtos);

        return userMeDto;

    }
}
