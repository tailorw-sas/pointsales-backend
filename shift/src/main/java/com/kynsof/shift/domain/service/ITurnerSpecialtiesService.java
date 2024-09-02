package com.kynsof.shift.domain.service;

import com.kynsof.shift.domain.dto.TurnerSpecialtiesDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ITurnerSpecialtiesService {
    void create(TurnerSpecialtiesDto dto);

    void create(List<TurnerSpecialtiesDto> dtos);

    void update(TurnerSpecialtiesDto dto);

    void delete(UUID id);

    TurnerSpecialtiesDto findById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

    List<TurnerSpecialtiesDto> findByShiftDateTimeBefore(LocalDateTime shiftDateTime);
}
