package com.kynsof.patients.domain.service;

import com.kynsof.patients.domain.dto.AdditionalInformationDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IAdditionalInfoService {
    UUID create(AdditionalInformationDto patients);

    UUID update(AdditionalInformationDto patients);

    void delete(UUID id);

    AdditionalInformationDto findById(UUID id);

    PaginatedResponse findAll(Pageable pageable);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}