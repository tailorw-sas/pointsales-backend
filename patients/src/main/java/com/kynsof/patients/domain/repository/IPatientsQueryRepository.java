package com.kynsof.patients.domain.repository;

import com.kynsof.patients.application.query.getall.PaginatedResponse;
import com.kynsof.patients.domain.PatientDto;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IPatientsQueryRepository {
    public PatientDto findById(UUID id);

    public PaginatedResponse findAll(Pageable pageable);

}
