package com.kynsof.rrhh.domain.interfaces.services;

import com.kynsof.rrhh.domain.dto.DoctorDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IDoctorService {
    void create(DoctorDto object);
    void update(DoctorDto object);
    void delete(DoctorDto object);
    DoctorDto findById(UUID id);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}