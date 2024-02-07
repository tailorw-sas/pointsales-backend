package com.kynsof.scheduled.domain.service;

import com.kynsof.scheduled.domain.dto.ReceiptDto;
public interface IReceiptService {
    public void create(ReceiptDto object);
//    public void delete(UUID id);
//    public ScheduleDto findById(UUID id);
//    public PaginatedResponse findAll(Pageable pageable, UUID idScheduled);
}