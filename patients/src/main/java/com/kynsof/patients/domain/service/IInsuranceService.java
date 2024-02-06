package com.kynsof.patients.domain.service;

import com.kynsof.patients.domain.dto.AllergyEntityDto;
import com.kynsof.patients.domain.dto.InsuranceDto;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.infrastructure.entity.Allergy;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IInsuranceService {

    InsuranceDto findById(UUID id);
    PaginatedResponse findAll(Pageable pageable, String name);
}