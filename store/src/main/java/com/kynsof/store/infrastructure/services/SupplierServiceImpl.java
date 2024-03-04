package com.kynsof.store.infrastructure.services;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.domain.dto.SupplierEntityDto;
import com.kynsof.store.domain.services.ISupplierService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public class SupplierServiceImpl implements ISupplierService {
    @Override
    public UUID create(SupplierEntityDto supplierDto) {
        return null;
    }

    @Override
    public UUID update(SupplierEntityDto supplierDto) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public SupplierEntityDto findById(UUID id) {
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
