package com.kynsof.patients.domain.service;

import com.kynsof.patients.domain.dto.DependentPatientDto;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.domain.dto.PatientDto;

import java.util.List;
import java.util.UUID;

import com.kynsof.share.core.domain.request.FilterCriteria;
import org.springframework.data.domain.Pageable;

public interface IPatientsService {
    UUID create(PatientDto patients);
    UUID createDependent(DependentPatientDto patients);
    UUID update(PatientDto patients);
    void updateDependent(DependentPatientDto patients);
    void delete(UUID id);

    PatientDto findById(UUID id);
    PaginatedResponse findAll(Pageable pageable);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

    void createInsurance(UUID patientId, List<UUID> insuranceIds);
}