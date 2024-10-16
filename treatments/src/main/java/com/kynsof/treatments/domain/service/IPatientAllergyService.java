package com.kynsof.treatments.domain.service;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.dto.PatientAllergyDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IPatientAllergyService {

    void create(PatientAllergyDto diagnosisDtoList);

    void update(PatientAllergyDto diagnosis);

    void delete(PatientAllergyDto treatment);
    void deleteByIds(List<UUID> ids);

    PatientAllergyDto findById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

}
