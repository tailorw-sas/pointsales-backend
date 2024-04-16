package com.kynsof.calendar.domain.service;

import com.kynsof.calendar.domain.dto.BusinessResourceDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IBusinessResourceService {
    void create(BusinessResourceDto object);
    void update(BusinessResourceDto object);
    void delete(UUID id);
    BusinessResourceDto findById(UUID id);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
    PaginatedResponse findResourceByBusinessId(Pageable pageable, UUID businessId);
    BusinessResourceDto findBusinessResourceByBusinessIdAndResourceId(UUID businessId, UUID resourceId);
    Long countBusinessResourceByBusinessIdAndResourceId(UUID businessId, UUID resourceId);
}