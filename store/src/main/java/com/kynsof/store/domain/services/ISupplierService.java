package com.kynsof.store.domain.services;


import com.kynsof.store.domain.dto.SupplierEntityDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface ISupplierService {
    UUID create(SupplierEntityDto supplierDto);
    UUID update(SupplierEntityDto supplierDto);
    void delete(UUID id);
    SupplierEntityDto findById(UUID id);
    PaginatedResponse findAll(Pageable pageable);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}

