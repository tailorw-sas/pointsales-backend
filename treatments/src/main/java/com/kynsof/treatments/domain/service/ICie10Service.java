package com.kynsof.treatments.domain.service;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.dto.Cie10Dto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICie10Service {

    Cie10Dto findByCode(String code);
    PaginatedResponse findAll(Pageable pageable, String name, String code);
    
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

}