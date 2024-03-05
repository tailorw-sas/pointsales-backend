package com.kynsof.store.domain.services;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.domain.dto.CustomerDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ICustomerService {
    UUID create(CustomerDto categoryDto);
    UUID update(CustomerDto categoryDto);
    void delete(UUID id);
    CustomerDto findById(UUID id);
    PaginatedResponse findAll(Pageable pageable);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
