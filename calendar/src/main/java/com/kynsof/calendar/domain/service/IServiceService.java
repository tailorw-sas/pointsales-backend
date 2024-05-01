package com.kynsof.calendar.domain.service;

import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IServiceService {
    ServiceDto create(ServiceDto object);
    ServiceDto update(ServiceDto object);
    void delete(UUID id);
    ServiceDto findById(UUID id);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
    PaginatedResponse findServicesByResourceId(Pageable pageable, UUID resourceId);
    Long countByNameAndNotId(String name, UUID id);
}