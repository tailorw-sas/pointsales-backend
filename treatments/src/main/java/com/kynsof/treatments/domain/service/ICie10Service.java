package com.kynsof.treatments.domain.service;

import com.kynsof.treatments.domain.dto.Cie10Dto;
import com.kynsof.treatments.domain.dto.PaginatedResponse;
import org.springframework.data.domain.Pageable;

public interface ICie10Service {

    Cie10Dto findByCode(String code);
    PaginatedResponse findAll(Pageable pageable, String name, String code);
}