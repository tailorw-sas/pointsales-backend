package com.kynsof.treatments.domain.service;

import com.kynsof.treatments.domain.dto.ExamOrderDto;
import com.kynsof.treatments.domain.dto.PaginatedResponse;
import com.kynsof.treatments.infrastructure.entity.ExamOrder;
import com.kynsof.treatments.infrastructure.entity.ExternalConsultation;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


public interface IExamOrderService {
     UUID create(ExamOrderDto externalConsultation);
    UUID update(ExamOrder externalConsultation);
     void delete(UUID id);
    ExamOrderDto findById(UUID id);
     PaginatedResponse findAll(Pageable pageable, UUID patientId);
}