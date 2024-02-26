package com.kynsof.treatments.domain.service;

import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.dto.PaginatedResponse;
import com.kynsof.treatments.infrastructure.entity.ExternalConsultation;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


public interface IExternalConsultationService {
     UUID create(ExternalConsultationDto externalConsultation);
    UUID update(ExternalConsultation externalConsultation);
     void delete(UUID id);
    ExternalConsultationDto findById(UUID id);
     PaginatedResponse findAll(Pageable pageable, UUID doctorId,  UUID patientId);
}