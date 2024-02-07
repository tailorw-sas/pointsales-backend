package com.kynsof.scheduled.domain.service;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.domain.dto.EStatusReceipt;
import com.kynsof.scheduled.domain.dto.ReceiptDto;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IReceiptService {
    public void create(ReceiptDto object);
    public void delete(UUID id);
    public ReceiptDto findById(UUID id);
    void update(ReceiptDto receipt, UUID idSchedule, UUID idService, EStatusReceipt status, Double price, boolean express, String reasons);
    PaginatedResponse findAll(Pageable pageable, String filter, UUID resource, UUID user, UUID service, UUID schedule, LocalDate date, LocalDate startDate, LocalDate endDate, EStatusReceipt status);
}