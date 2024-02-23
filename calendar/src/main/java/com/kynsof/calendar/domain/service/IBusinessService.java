package com.kynsof.calendar.domain.service;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IBusinessService {
    public void create(BusinessDto object);
    public void update(BusinessDto object);
    public void delete(UUID id);
    public BusinessDto findById(UUID id);
    public PaginatedResponse findAll(Pageable pageable, UUID idObject, String filter);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}