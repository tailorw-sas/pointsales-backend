package com.kynsof.shift.domain.service;

import com.kynsof.shift.domain.dto.TurnDto;
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

    List<TurnDto> findByServiceIds(List<UUID> serviceId, UUID businessId);

    List<TurnDto>  findByLocalId(String local, UUID businessId);

    Integer findMaxOrderNumberByServiceId( UUID serviceId, UUID businessId);
}