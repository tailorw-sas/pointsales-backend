package com.kynsof.store.infrastructure.services;

import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.store.application.query.supplier.getAll.SupplierResponse;
import com.kynsof.store.domain.dto.SupplierEntityDto;
import com.kynsof.store.domain.services.ISupplierService;
import com.kynsof.store.infrastructure.entity.Subcategory;
import com.kynsof.store.infrastructure.entity.Supplier;
import com.kynsof.store.infrastructure.repositories.command.SupplierWriteDataJPARepository;
import com.kynsof.store.infrastructure.repositories.queries.SupplierReadDataJPARepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        if (supplierDto == null || supplierDto.getId() == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }

        return repositoryQuery.findById(supplierDto.getId())
                .map(supplier -> {
                    supplier.setName(supplierDto.getName() != null ? supplierDto.getName() : supplier.getName());
                    supplier.setAddress(supplierDto.getAddress() != null ? supplierDto.getAddress() : supplier.getAddress());
                    supplier.setPhone(supplierDto.getPhone() != null ? supplierDto.getPhone() : supplier.getPhone());
                    supplier.setEmail(supplierDto.getEmail() != null ? supplierDto.getEmail() : supplier.getEmail());
                    supplier.setUpdatedAt(LocalDateTime.now());
                    return repositoryCommand.save(supplier);
                })
                .orElseThrow(() -> new EntityNotFoundException("Supplier with ID " + supplierDto.getId() + " not found"))
                .getId();
    }

    @Override
    public void delete(UUID id) {
        try {
            this.repositoryCommand.deleteById(id);
        } catch (Exception e) {
            throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.NOT_DELETE, new ErrorField("id", "Element cannot be deleted has a related element.")));
        }
    }

    @Override
    public SupplierEntityDto findById(UUID id) {
        Optional<Supplier> supplier = this.repositoryQuery.findById(id);
        if (supplier.isPresent()) {
            return supplier.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Supplier not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable) {

        Page<Supplier> data = this.repositoryQuery.findAll(pageable);

        return getPaginatedResponse(data);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<Subcategory> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Supplier> data = this.repositoryQuery.findAll(specifications, pageable);

        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Supplier> data) {
        List<SupplierResponse> patients = new ArrayList<>();
        for (Supplier p : data.getContent()) {
            patients.add(new SupplierResponse(p.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
