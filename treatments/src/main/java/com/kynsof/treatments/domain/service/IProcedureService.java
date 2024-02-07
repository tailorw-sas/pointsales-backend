package com.kynsof.treatments.domain.service;

import com.kynsof.treatments.domain.dto.PaginatedResponse;
import com.kynsof.treatments.domain.dto.ProcedureDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IProcedureService {

    ProcedureDto findByCode(String code);
    PaginatedResponse findAll(Pageable pageable, String name, String code, String type);
}