package com.kynsof.patients.domain.service;

import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.domain.dto.PatientDto;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IPatientsService {
    public UUID create(PatientDto patients);
    UUID update(PatientDto patients);
    public void delete(UUID id);
    public PatientDto findById(UUID id);
    public PaginatedResponse findAll(Pageable pageable, UUID idPatients, String identification);
}