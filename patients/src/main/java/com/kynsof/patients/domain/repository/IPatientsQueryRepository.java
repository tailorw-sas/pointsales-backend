package com.kynsof.patients.domain.repository;

import com.kynsof.patients.application.query.getall.PaginatedResponse;
import com.kynsof.patients.domain.Patients;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IPatientsQueryRepository {
    public Patients findById(UUID id);

    public PaginatedResponse findAll(Pageable pageable);

}
