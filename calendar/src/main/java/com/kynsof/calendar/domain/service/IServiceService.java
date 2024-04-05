package com.kynsof.calendar.domain.service;

import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IServiceService {
    void create(ServiceDto object);
    void update(ServiceDto object);
    void delete(UUID id);
    ServiceDto findById(UUID id);
    PaginatedResponse findAll(Pageable pageable, UUID idObject, String filter);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}