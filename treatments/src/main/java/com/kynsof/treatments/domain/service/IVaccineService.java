package com.kynsof.treatments.domain.service;

import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.dto.VaccineDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IVaccineService {

    VaccineDto findById(UUID id);
    PaginatedResponse findAll(Pageable pageable, String name, String description);
    List<VaccineDto> getApplicableVaccines(LocalDate birthDate, UUID patientId);

    UUID create(VaccineDto vaccineDto);
}