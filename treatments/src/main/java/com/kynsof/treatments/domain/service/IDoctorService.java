package com.kynsof.treatments.domain.service;

import com.kynsof.treatments.domain.dto.DoctorDto;

import java.util.UUID;

public interface IDoctorService {
    UUID create(DoctorDto patients);
    UUID update(DoctorDto patients);
    void delete(UUID id);
    DoctorDto findById(UUID id);
}