package com.kynsof.store.domain.services;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.domain.dto.CategoryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    UUID create(CategoryDto categoryDto);
    UUID update(CategoryDto categoryDto);
    void delete(UUID id);
    CategoryDto findById(UUID id);
    PaginatedResponse findAll(Pageable pageable);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
