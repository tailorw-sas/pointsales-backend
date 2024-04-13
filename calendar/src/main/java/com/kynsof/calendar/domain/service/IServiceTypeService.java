package com.kynsof.calendar.domain.service;

import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IServiceTypeService {
    public void create(ServiceTypeDto object);
    public void update(ServiceTypeDto object);
    public void delete(UUID id);
    public ServiceTypeDto findById(UUID id);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
    Long countByNameAndNotId(String name, UUID id);
}