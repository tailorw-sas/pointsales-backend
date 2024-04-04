package com.kynsof.identity.domain.interfaces.service;

import com.kynsof.identity.domain.dto.UserRoleBusinessDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IUserRoleBusinessService {
    void create(List<UserRoleBusinessDto> userRoleBusiness);
    void update(List<UserRoleBusinessDto> userRoleBusiness);
    void delete(UUID id); 
    void delete(List<UserRoleBusinessDto> userRoleBusiness);
    UserRoleBusinessDto findById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
