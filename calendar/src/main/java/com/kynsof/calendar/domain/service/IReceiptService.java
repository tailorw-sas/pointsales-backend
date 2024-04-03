package com.kynsof.calendar.domain.service;

import com.kynsof.calendar.domain.dto.ReceiptDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusReceipt;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IReceiptService {
    UUID  create(ReceiptDto object);
    public void delete(UUID id);
    public ReceiptDto findById(UUID id);
    void update(ReceiptDto receipt);
    PaginatedResponse findAll(Pageable pageable, String filter, UUID resource, UUID user, UUID service, UUID schedule, LocalDate date, LocalDate startDate, LocalDate endDate, EStatusReceipt status);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}