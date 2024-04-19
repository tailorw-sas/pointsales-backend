package com.kynsof.treatments.domain.service;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.dto.ProcedureDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IProcedureService {

    void create(ProcedureDto patients);

    void update(ProcedureDto patients);

    void delete(UUID id);

    ProcedureDto findById(UUID code);

    ProcedureDto findByCode(String code);

    PaginatedResponse findAll(Pageable pageable, String name, String code, String type);
    
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

}
