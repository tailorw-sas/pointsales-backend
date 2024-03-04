package com.kynsof.store.domain.services;


import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.domain.dto.ProductEntityDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    UUID create(ProductEntityDto productDto);
    UUID update(ProductEntityDto productDto);
    void delete(UUID id);
    ProductEntityDto findById(UUID id);
    PaginatedResponse findAll(Pageable pageable);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
