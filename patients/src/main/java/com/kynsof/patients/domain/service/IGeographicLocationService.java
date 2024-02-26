package com.kynsof.patients.domain.service;

import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.share.core.domain.request.FilterCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IGeographicLocationService {
     GeographicLocationDto findById(UUID id);
     PaginatedResponse findAll(Pageable pageable);
     PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}