package com.kynsof.patients.domain.service;

import com.kynsof.patients.domain.dto.MedicalInformationDto;
import com.kynsof.patients.domain.dto.MedicalInformationUpdateDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IMedicalInformationService {
    UUID create(MedicalInformationDto patients);

    UUID update(MedicalInformationUpdateDto patients);

    void delete(UUID id);

    MedicalInformationDto findById(UUID id);

    PaginatedResponse findAll(Pageable pageable);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}