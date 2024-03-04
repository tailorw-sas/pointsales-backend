package com.kynsof.store.infrastructure.services;


import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.store.application.query.category.getAll.CategoryResponse;
import com.kynsof.store.domain.dto.CategoryDto;
import com.kynsof.store.domain.services.ICategoryService;
import com.kynsof.store.infrastructure.entity.Category;
import com.kynsof.store.infrastructure.repositories.command.CategoryWriteDataJPARepository;
import com.kynsof.store.infrastructure.repositories.queries.CategoryReadDataJPARepository;
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
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryWriteDataJPARepository repositoryCommand;

    @Autowired
    private CategoryReadDataJPARepository repositoryQuery;
    @Override
    public UUID create(CategoryDto categoryDto) {
        Category additionalInformation = this.repositoryCommand.save(new Category(categoryDto));
        return additionalInformation.getId();
    }

    @Override
    public UUID update(CategoryDto categoryDto) {

        if (categoryDto == null || categoryDto.getId() == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        return repositoryQuery.findById(categoryDto.getId())
                .map(category -> {
                    category.setName(categoryDto.getName() != null ? categoryDto.getName() : category.getName());
                    category.setDescription(categoryDto.getDescription() != null ?
                            categoryDto.getDescription() : category.getDescription());
                    return repositoryCommand.save(category);
                })
                .orElseThrow(() -> new EntityNotFoundException("Category with ID " + categoryDto.getId() + " not found"))
                .getId();
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public CategoryDto findById(UUID id) {
        Optional<Category> category = this.repositoryQuery.findById(id);
        if (category.isPresent()) {
            return category.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Category not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable) {
        Page<Category> data = this.repositoryQuery.findAll( pageable);

        return getPaginatedResponse(data);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {

        GenericSpecificationsBuilder<Category> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Category> data = this.repositoryQuery.findAll(specifications, pageable);

        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Category> data) {
        List<CategoryResponse> patients = new ArrayList<>();
        for (Category p : data.getContent()) {
            patients.add(new CategoryResponse(p.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
