package com.kynsof.store.infrastructure.services;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.domain.dto.SubcategoryEntityDto;
import com.kynsof.store.domain.services.ISubcategoryService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public class SubcategoryServiceImpl implements ISubcategoryService {
    @Override
    public UUID create(SubcategoryEntityDto subcategoryDto) {
        return null;
    }

    @Override
    public UUID update(SubcategoryEntityDto subcategoryDto) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public SubcategoryEntityDto findById(UUID id) {
        return null;
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable) {
        return null;
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        return null;
    }
}
