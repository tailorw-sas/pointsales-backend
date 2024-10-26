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
    ServiceDto findByIds(UUID id);
    List<ServiceDto> findByIds(List<UUID> ids);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
    PaginatedResponse findServicesByResourceId(Pageable pageable, UUID resourceId);
    Long countByNameAndNotId(String name, UUID id);
    Long countByCodeAndNotId(String code, UUID id);
    List<ServiceDto> findAllToReplicate();
}