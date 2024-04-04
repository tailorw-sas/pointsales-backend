package com.kynsof.identity.domain.interfaces.service;

import com.kynsof.identity.domain.dto.BusinessModuleDto;
import com.kynsof.identity.domain.dto.ModuleDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IBusinessModuleService {
    void create(BusinessModuleDto object);
    void create(List<BusinessModuleDto> objects);
    void update(BusinessModuleDto object);
    void delete(UUID id);
    BusinessModuleDto findById(UUID id);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

    List<ModuleDto> findModulesByBusinessId(UUID businessId);
}