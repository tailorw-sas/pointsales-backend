package com.kynsof.treatments.domain.service;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface ITreatmentService {

    void create(TreatmentDto treatment);

    void update(TreatmentDto treatment);

    void delete(UUID id);

    TreatmentDto findById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

    PaginatedResponse findByExternalConsultation(ExternalConsultationDto externalConsultation, Pageable pageable);
}
