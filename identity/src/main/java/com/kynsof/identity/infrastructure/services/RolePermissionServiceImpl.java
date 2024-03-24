package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.application.query.rolpermission.getById.RolPermissionResponse;
import com.kynsof.identity.domain.dto.RolePermissionDto;
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

    @Override
    public UUID create(RolePermissionDto dto) {
        RolePermission response = this.repositoryCommand.save(new RolePermission(dto));
        return response.getId();
    }

    @Override
    public void update(RolePermissionDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(UUID id) {
        RolePermissionDto deleteObject = this.findById(id);

        deleteObject.setDeleted(true);
        deleteObject.setDeletedAt(ConfigureTimeZone.getTimeZone());
        
        this.repositoryCommand.save(new RolePermission(deleteObject));
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
