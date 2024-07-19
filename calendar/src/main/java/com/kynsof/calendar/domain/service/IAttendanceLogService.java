package com.kynsof.calendar.domain.service;

import com.kynsof.calendar.domain.dto.AttendanceLogDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IAttendanceLogService {
    UUID create(AttendanceLogDto object);

    void update(AttendanceLogDto object);

    void delete(UUID id);

    AttendanceLogDto findById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
