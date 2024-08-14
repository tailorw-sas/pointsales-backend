package com.kynsof.calendar.domain.service;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IBusinessService {
    void create(BusinessDto object);

    void update(BusinessDto object);

    void delete(UUID id);
    void deleteIds(List<UUID> ids);

    BusinessDto findById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

    PaginatedResponse findBusinessesWithAvailableStockByDateAndService(LocalDate date, UUID serviceId,
                                                                       Pageable pageable);

    PaginatedResponse findDetailedAvailableSchedulesByResourceAndBusinessAndDateRange(LocalDate startDate, LocalDate endDate, UUID serviceId,
                                                                                      String businessName, Pageable pageable);
}