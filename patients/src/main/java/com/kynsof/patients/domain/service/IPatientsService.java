package com.kynsof.patients.domain.service;

import com.kynsof.patients.application.query.getall.PaginatedResponse;
import com.kynsof.patients.domain.Patients;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IPatientsService {
    public void createService(Patients patients);
    public void delete(UUID id);
    public Patients findById(UUID id);
    public PaginatedResponse findAll(Pageable pageable, UUID idPatients, String identification);
}