package com.kynsoft.rrhh.domain.interfaces.services;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsoft.rrhh.domain.dto.AssistantDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IAssistantService {
    void create(AssistantDto object);
    void update(AssistantDto object);
    void delete(AssistantDto object);
    AssistantDto findById(UUID id);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
    Long countByIdentificationAndNotId(String identification);
    Long countByEmailAndNotId(String email);
}