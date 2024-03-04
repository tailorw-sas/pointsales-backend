package com.kynsof.store.infrastructure.services;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.domain.dto.SupplierEntityDto;
import com.kynsof.store.domain.services.ISupplierService;
import com.kynsof.store.infrastructure.entity.Supplier;
import com.kynsof.store.infrastructure.repositories.command.SupplierWriteDataJPARepository;
import com.kynsof.store.infrastructure.repositories.queries.SupplierReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class SupplierServiceImpl implements ISupplierService {
    @Autowired
    private SupplierWriteDataJPARepository repositoryCommand;

    @Autowired
    private SupplierReadDataJPARepository repositoryQuery;
    @Override
    public UUID create(SupplierEntityDto supplierDto) {
        Supplier additionalInformation = this.repositoryCommand.save(new Supplier(supplierDto));
        return additionalInformation.getId();
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
