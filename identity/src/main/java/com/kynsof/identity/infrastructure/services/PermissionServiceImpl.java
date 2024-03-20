package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.dto.enumType.PermissionStatusEnm;
import com.kynsof.identity.domain.interfaces.service.IPermissionService;
import com.kynsof.identity.infrastructure.identity.Permission;
import com.kynsof.identity.infrastructure.repository.command.PermissionWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.PermissionReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired 
    private PermissionWriteDataJPARepository writeRepository;

    @Autowired
    private PermissionReadDataJPARepository queryRepository;

    @Override
    public UUID create(PermissionDto dto) {
        dto.setStatus(PermissionStatusEnm.ACTIVE);
        this.writeRepository.save(new Permission(dto));
        return dto.getId();
    }

    @Override
    public void update(PermissionDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PermissionDto findById(UUID id) {
        Optional<Permission> object = this.queryRepository.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessException(DomainErrorMessage.PERMISSION_NOT_FOUND, "Permission not found.");
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
