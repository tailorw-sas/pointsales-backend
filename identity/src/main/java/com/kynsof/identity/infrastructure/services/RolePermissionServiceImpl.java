package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.application.query.rolpermission.getById.RolPermissionResponse;
import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.dto.RolePermissionDto;
import com.kynsof.identity.domain.interfaces.IRoleService;
import com.kynsof.identity.domain.interfaces.service.IRolePermissionService;
import com.kynsof.identity.infrastructure.identity.RolePermission;
import com.kynsof.identity.infrastructure.repository.command.RolPermissionWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.RolPermissionReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.share.utils.ConfigureTimeZone;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;

@Service
public class RolePermissionServiceImpl implements IRolePermissionService {

    @Autowired
    private RolPermissionWriteDataJPARepository repositoryCommand;

    @Autowired
    private RolPermissionReadDataJPARepository repositoryQuery;

    @Autowired
    private IRoleService roleService;

    @Override
    public void create(List<RolePermissionDto> permissions) {
        List<RolePermission> savePermissions = new ArrayList<>();
        for (RolePermissionDto permission : permissions) {
            permission.setDeleted(false);
            permission.setDeletedAt(null);
            System.err.println("#################################");
            System.err.println("#################################");
            System.err.println("Valor> " + permission.getId());
            System.err.println("#################################");
            System.err.println("#################################");
            savePermissions.add(new RolePermission(permission));
        }
        this.repositoryCommand.saveAll(savePermissions);
    }

    @Override
    public void update(RoleDto role, List<PermissionDto> permissions) {
        List<RolePermissionDto> rolesPermissions = this.roleService.findPermissionForRoleById(role.getId());

        System.err.println("Cantidad de permisos: " + rolesPermissions.size());
    }

    @Override
    public void delete(UUID id) {
        RolePermissionDto deleteObject = this.findById(id);

        deleteObject.setDeleted(true);
        deleteObject.setDeletedAt(ConfigureTimeZone.getTimeZone());

        this.repositoryCommand.save(new RolePermission(deleteObject));
    }

    @Override
    public void delete(List<RolePermissionDto> deletePermissions) {
        List<RolePermission> savePermissions = new ArrayList<>();
        for (RolePermissionDto deletePermission : deletePermissions) {
            deletePermission.setDeleted(true);
            deletePermission.setDeletedAt(ConfigureTimeZone.getTimeZone());
            
            savePermissions.add(new RolePermission(deletePermission));
        }
        this.repositoryCommand.saveAll(savePermissions);
    }

    @Override
    public RolePermissionDto findById(UUID id) {
        Optional<RolePermission> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessException(DomainErrorMessage.ROLE_PERMISSION_NOT_FOUND, "RolPermission not found.");
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<RolePermission> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<RolePermission> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<RolePermission> data) {
        List<RolPermissionResponse> rolesPermissions = new ArrayList<>();
        for (RolePermission o : data.getContent()) {
            rolesPermissions.add(new RolPermissionResponse(o.toAggregate()));
        }
        return new PaginatedResponse(rolesPermissions, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

}
