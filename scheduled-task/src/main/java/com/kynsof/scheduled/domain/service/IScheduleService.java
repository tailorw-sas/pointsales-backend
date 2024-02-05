package com.kynsof.scheduled.domain.service;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.domain.dto.ScheduleDto;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IScheduleService {
    public void createService(ScheduleDto patients);
    public void delete(UUID id);
    public ScheduleDto findById(UUID id);
    public PaginatedResponse findAll(Pageable pageable, UUID idScheduled);
}