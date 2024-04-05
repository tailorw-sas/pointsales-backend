package com.kynsof.identity.domain.interfaces.service;

import com.kynsof.identity.domain.dto.UserPermissionBusinessDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IUserPermissionBusinessService {
    void create(List<UserPermissionBusinessDto> userRoleBusiness);
    void update(List<UserPermissionBusinessDto> userRoleBusiness);
    void delete(UUID id); 
    void delete(List<UserPermissionBusinessDto> userRoleBusiness);
    UserPermissionBusinessDto findById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

    List<UserPermissionBusinessDto> findByUserAndBusiness(UUID userSystemId, UUID businessId);
}
