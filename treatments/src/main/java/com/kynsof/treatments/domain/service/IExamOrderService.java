package com.kynsof.treatments.domain.service;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.dto.ExamOrderDto;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.infrastructure.entity.ExamOrder;
import java.util.List;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IExamOrderService {

    UUID create(ExamOrderDto externalConsultation);

    UUID update(ExamOrder externalConsultation);

    void delete(UUID id);

    ExamOrderDto findById(UUID id);

    PaginatedResponse findAll(Pageable pageable, UUID patientId);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

    ExamOrderDto findByExternalConsultation(ExternalConsultationDto externalConsultation);
}
