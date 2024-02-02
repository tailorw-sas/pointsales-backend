package com.kynsof.patients.domain.service;

import com.kynsof.patients.domain.dto.AdditionalInformationDto;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IAdditionalInfoService {
     UUID create(AdditionalInformationDto patients);
    UUID update(AdditionalInformationDto patients);
     void delete(UUID id);
     AdditionalInformationDto findById(UUID id);
     PaginatedResponse findAll(Pageable pageable, UUID idPatients,  String emergencyContactName);
}