package com.kynsof.store.infrastructure.services;

import com.kynsof.store.domain.dto.SubcategoryEntityDto;
import com.kynsof.store.domain.services.ISubcategoryService;
import com.kynsof.store.infrastructure.entity.Category;
import com.kynsof.store.infrastructure.entity.Subcategory;
import com.kynsof.store.infrastructure.repositories.command.SubcategoryWriteDataJPARepository;
import com.kynsof.store.infrastructure.repositories.queries.SubcategoryReadDataJPARepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//
//    @Override
//    public void delete(UUID id) {
//
//    }
//
//    @Override
//    public SubcategoryEntityDto findById(UUID id) {
//        return null;
//    }
//
//    @Override
//    public PaginatedResponse findAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
//        return null;
//    }
}
