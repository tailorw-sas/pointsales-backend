package com.kynsof.calendar.domain.service;

import com.kynsof.calendar.domain.dto.EStatusReceipt;
import com.kynsof.calendar.domain.dto.ReceiptDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IReceiptService {
    public void create(ReceiptDto object);
    public void delete(UUID id);
    public ReceiptDto findById(UUID id);
    void update(ReceiptDto receipt, UUID idSchedule, UUID idService, EStatusReceipt status, Double price, boolean express, String reasons);
    PaginatedResponse findAll(Pageable pageable, String filter, UUID resource, UUID user, UUID service, UUID schedule, LocalDate date, LocalDate startDate, LocalDate endDate, EStatusReceipt status);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}