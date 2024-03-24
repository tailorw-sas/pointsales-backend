package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.domain.dto.RolePermissionDto;
import com.kynsof.identity.domain.interfaces.service.IRolePermissionService;
import com.kynsof.identity.infrastructure.identity.RolePermission;
import com.kynsof.identity.infrastructure.repository.command.RolPermissionWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.RolPermissionReadDataJPARepository;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public RolePermissionDto findById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
