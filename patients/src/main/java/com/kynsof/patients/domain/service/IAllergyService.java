package com.kynsof.patients.domain.service;

import com.kynsof.patients.domain.dto.AllergyEntityDto;
import com.kynsof.patients.infrastructure.entity.Allergy;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IAllergyService {
    UUID create(AllergyEntityDto patients);

    UUID update(Allergy patients);

    void delete(UUID id);

    AllergyEntityDto findById(UUID id);

    PaginatedResponse findAll(Pageable pageable);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}