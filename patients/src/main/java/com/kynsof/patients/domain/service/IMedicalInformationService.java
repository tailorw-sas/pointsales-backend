package com.kynsof.patients.domain.service;

import com.kynsof.patients.domain.dto.MedicalInformationDto;
import com.kynsof.patients.domain.dto.MedicalInformationUpdateDto;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.infrastructure.entity.MedicalInformation;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IMedicalInformationService {
     UUID create(MedicalInformationDto patients);
    UUID update(MedicalInformationUpdateDto patients);
     void delete(UUID id);
     MedicalInformationDto findById(UUID id);
     PaginatedResponse findAll(Pageable pageable, UUID idPatients);
}