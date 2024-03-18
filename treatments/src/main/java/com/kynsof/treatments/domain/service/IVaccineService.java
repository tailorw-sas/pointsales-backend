package com.kynsof.treatments.domain.service;

import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.dto.VaccineDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IVaccineService {

    VaccineDto findById(UUID id);
    PaginatedResponse findAll(Pageable pageable, String name, String description);
}