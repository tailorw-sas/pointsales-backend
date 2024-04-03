package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.domain.dto.ModuleDto;
import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.interfaces.service.IBusinessModuleService;
import com.kynsof.identity.infrastructure.identity.BusinessModule;
import com.kynsof.identity.infrastructure.identity.ModuleSystem;
import com.kynsof.identity.infrastructure.identity.Permission;
import com.kynsof.identity.infrastructure.repository.command.BusinessModuleWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.BusinessModuleReadDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.PermissionReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BusinessModuleServiceImpl implements IBusinessModuleService {

    @Autowired
    private BusinessModuleWriteDataJPARepository commandRepository;
    @Autowired
    private BusinessModuleReadDataJPARepository businessModuleRepository;
    @Autowired
    private PermissionReadDataJPARepository permissionRepository;

    @Override
    public List<ModuleDto> findModulesByBusinessId(UUID businessId) {
        List<BusinessModule> businessModules = businessModuleRepository.findByBusinessId(businessId);

        return businessModules.stream().map(businessModule -> {
            ModuleSystem module = businessModule.getModule();
            Set<PermissionDto> permissionsDto = new HashSet<>();
            Set<Permission> permissions = module.getPermissions();
            permissions.forEach(permission -> permissionsDto.add(
                    new PermissionDto(
                            permission.getId(),
                            permission.getCode(),
                            permission.getDescription(),
                            null,
                            permission.getStatus(),
                            permission.getAction()
                    )
            ));

            return new ModuleDto(module.getId(), module.getName(), module.getImage(), module.getDescription(), permissionsDto);
        }).collect(Collectors.toList());
    }
}
