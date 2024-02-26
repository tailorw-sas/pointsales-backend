package com.kynsof.patients.domain.service;

import com.kynsof.patients.domain.dto.CurrentMerdicationEntityDto;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.infrastructure.entity.CurrentMedication;
import com.kynsof.share.core.domain.request.FilterCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ICurrentMedicationService {
    UUID create(CurrentMerdicationEntityDto patients);

    UUID update(CurrentMedication patients);

    void delete(UUID id);

    CurrentMerdicationEntityDto findById(UUID id);

    PaginatedResponse findAll(Pageable pageable);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}