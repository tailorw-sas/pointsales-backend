package com.kynsof.calendar.domain.service;

import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IResourceService {
    void create(ResourceDto object);

    void update(ResourceDto object);

    void delete(UUID id);

    ResourceDto findById(UUID id);

    PaginatedResponse findAll(Pageable pageable, UUID idObject, String filter);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

    PaginatedResponse findResourcesWithAvailableSchedules(UUID businessId, UUID serviceId, LocalDate date,
                                                                       Pageable pageable);

    void addBusiness(UUID businessId, UUID serviceId, LocalDate date);
    void addServicesToResource(UUID resourceId, List<UUID> serviceIds);
    PaginatedResponse findResourcesByServiceId(UUID businessId, UUID serviceId, Pageable pageable);

    List<ServiceDto> getAllServicesByResourceAndBusiness(UUID resourceId, UUID businessId);
}