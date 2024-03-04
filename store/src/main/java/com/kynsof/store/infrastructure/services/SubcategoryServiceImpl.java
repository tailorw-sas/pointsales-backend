package com.kynsof.store.infrastructure.services;

import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.store.application.query.subcategory.getAll.SubcategoryResponse;
import com.kynsof.store.domain.dto.SubcategoryEntityDto;
import com.kynsof.store.domain.services.ISubcategoryService;
import com.kynsof.store.infrastructure.entity.Category;
import com.kynsof.store.infrastructure.entity.Subcategory;
import com.kynsof.store.infrastructure.repositories.command.SubcategoryWriteDataJPARepository;
import com.kynsof.store.infrastructure.repositories.queries.SubcategoryReadDataJPARepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubcategoryServiceImpl implements ISubcategoryService {
    @Autowired
    private SubcategoryWriteDataJPARepository repositoryCommand;
    @Autowired
    private SubcategoryReadDataJPARepository repositoryQuery;

    @Override
    public UUID create(SubcategoryEntityDto subcategoryDto) {
        Subcategory save = this.repositoryCommand.save(new Subcategory(subcategoryDto));
        return save.getId();
    }

    @Override
    public UUID update(SubcategoryEntityDto subcategoryDto) {
        if (subcategoryDto == null || subcategoryDto.getId() == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        return repositoryQuery.findById(subcategoryDto.getId())
                .map(subcategory -> {
                    subcategory.setName(subcategoryDto.getName() != null ? subcategoryDto.getName() : subcategory.getName());
                    subcategory.setDescription(subcategoryDto.getDescription() != null ?
                            subcategoryDto.getDescription() : subcategory.getDescription());
                    subcategory.setCategory(subcategoryDto.getCategoryId() != null ?
                            new Category(subcategoryDto.getCategory()) : subcategory.getCategory());
                    return repositoryCommand.save(subcategory);
                })
                .orElseThrow(() -> new EntityNotFoundException("SubCategory with ID " + subcategoryDto.getId() + " not found"))
                .getId();
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public SubcategoryEntityDto findById(UUID id) {
        Optional<Subcategory> subcategory = this.repositoryQuery.findById(id);
        if (subcategory.isPresent()) {
            return subcategory.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Subcategory not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable) {
        Page<Subcategory> data = this.repositoryQuery.findAll( pageable);

        return getPaginatedResponse(data);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<Subcategory> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Subcategory> data = this.repositoryQuery.findAll(specifications, pageable);

        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Subcategory> data) {
        List<SubcategoryResponse> patients = new ArrayList<>();
        for (Subcategory p : data.getContent()) {
            patients.add(new SubcategoryResponse(p.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
