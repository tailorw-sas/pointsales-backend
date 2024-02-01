package com.kynsof.scheduled.domain.service;

import com.kynsof.scheduled.application.query.PaginatedResponse;
import com.kynsof.scheduled.domain.Scheduled;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IScheduleService {
    public void createService(Scheduled patients);
    public void delete(UUID id);
    public Scheduled findById(UUID id);
    public PaginatedResponse findAll(Pageable pageable, UUID idScheduled);
}