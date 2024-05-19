package com.kynsof.treatments.domain.service;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.dto.ExamOrderDto;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IExamOrderService {

    UUID create(ExamOrderDto externalConsultation);

    void update(ExamOrderDto externalConsultation);

    void delete(ExamOrderDto dto);

    ExamOrderDto findById(UUID id);

    PaginatedResponse findAll(Pageable pageable, UUID patientId);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

    ExamOrderDto findByExternalConsultation(ExternalConsultationDto externalConsultation);
}
