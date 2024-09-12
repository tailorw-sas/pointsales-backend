package com.kynsof.shift.domain.service;

import com.kynsof.shift.domain.dto.PlaceDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IPlaceService {
    UUID create(PlaceDto object);

    void update(PlaceDto object);

    void delete(UUID id);

    PlaceDto findById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

    Long countByCodeAndNotId(String code, UUID id);
}