package com.kynsof.identity.domain.interfaces.service;

import com.kynsof.identity.domain.dto.ModuleDto;

import java.util.List;
import java.util.UUID;

public interface IBusinessModuleService {
//    void create(BusinessModuleDto object);
//    void update(BusinessModuleDto object);
//    void delete(UUID id);
//    BusinessModuleDto findById(UUID id);
//    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

    List<ModuleDto> findModulesByBusinessId(UUID businessId);
}