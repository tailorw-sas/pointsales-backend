package com.kynsof.identity.domain.interfaces.service;

import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.dto.RolePermissionDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IRoleService {
    UUID create(RoleDto dto);
    void update(RoleDto dto);
    void delete(UUID id);

    RoleDto findById(UUID id);
    List<RolePermissionDto> findPermissionForRoleById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
