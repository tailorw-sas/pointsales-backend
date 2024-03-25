package com.kynsof.identity.domain.interfaces.service;

import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.dto.RolePermissionDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IRolePermissionService {
    void create(List<RolePermissionDto> permissions);
    void update(RoleDto role, List<PermissionDto> permissions);
    void delete(UUID id);
    void delete(List<RolePermissionDto> deletePermissions);
    RolePermissionDto findById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
