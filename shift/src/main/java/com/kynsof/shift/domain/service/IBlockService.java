package com.kynsof.shift.domain.service;

import com.kynsof.shift.domain.dto.BlockDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IBlockService {
    UUID create(BlockDto object);

    void update(BlockDto object);

    void delete(UUID id);

    BlockDto findById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

    Long countByCodeAndNotId(String code, UUID id);

    Long countByNameAndNotId(String name, UUID id);
}