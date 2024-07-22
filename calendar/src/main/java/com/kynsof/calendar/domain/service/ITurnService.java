package com.kynsof.calendar.domain.service;

import com.kynsof.calendar.domain.dto.TurnDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ITurnService {
    UUID create(TurnDto object);

    void update(TurnDto object);

    void delete(UUID id);

    TurnDto findById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

    List<TurnDto> findByServiceId(UUID serviceId, UUID businessId);

    TurnDto findByLocalId(String local, UUID businessId);

    List<TurnDto> findByServiceByFinanceId(UUID businessId);

    int findPositionByServiceId(UUID id, UUID businessDtoId);
}