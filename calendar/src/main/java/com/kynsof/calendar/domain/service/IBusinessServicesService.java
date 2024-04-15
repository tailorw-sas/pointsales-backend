package com.kynsof.calendar.domain.service;

import com.kynsof.calendar.domain.dto.BusinessServicesDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IBusinessServicesService {
    void create(BusinessServicesDto object);
    void update(BusinessServicesDto object);
    void delete(UUID id);
    BusinessServicesDto findById(UUID id);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
    PaginatedResponse findServicesByBusinessId(Pageable pageable, UUID businessId);
}