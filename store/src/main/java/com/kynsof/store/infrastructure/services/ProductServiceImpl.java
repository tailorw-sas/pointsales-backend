package com.kynsof.store.infrastructure.services;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.domain.dto.ProductEntityDto;
import com.kynsof.store.domain.services.IProductService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public class ProductServiceImpl implements IProductService {
    @Override
    public UUID create(ProductEntityDto productDto) {
        return null;
    }

    @Override
    public UUID update(ProductEntityDto productDto) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public ProductEntityDto findById(UUID id) {
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
