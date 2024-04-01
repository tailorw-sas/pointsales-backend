package com.kynsof.identity.domain.interfaces.service;

import com.kynsof.identity.domain.dto.ModuleDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IModuleService {
    public void create(ModuleDto object);
    public void update(ModuleDto object);
    public void delete(UUID id);
    public ModuleDto findById(UUID id);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}