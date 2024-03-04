package com.kynsof.store.domain.services;

import com.kynsof.store.domain.dto.SubcategoryEntityDto;

import java.util.UUID;

public interface ISubcategoryService {
    UUID create(SubcategoryEntityDto subcategoryDto);

    UUID update(SubcategoryEntityDto subcategoryDto);
//    void delete(UUID id);
//    SubcategoryEntityDto findById(UUID id);
//    PaginatedResponse findAll(Pageable pageable);
//    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}

