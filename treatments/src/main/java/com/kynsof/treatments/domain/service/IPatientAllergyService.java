package com.kynsof.treatments.domain.service;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.dto.PathologicalHistoryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IPatientAllergyService {

    UUID create(PathologicalHistoryDto diagnosisDtoList);

    void update(PathologicalHistoryDto diagnosis);

    void delete(PathologicalHistoryDto treatment);
    void deleteByIds(List<UUID> ids);

    PathologicalHistoryDto findById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

}
