package com.kynsof.identity.domain.interfaces.service;

import com.kynsof.identity.domain.dto.RolePermissionDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IRolePermissionService {
    UUID create(RolePermissionDto dto);
    void update(RolePermissionDto dto);
    void delete(UUID id);

    RolePermissionDto findById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
