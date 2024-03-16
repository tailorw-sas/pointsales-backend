package com.kynsof.identity.domain.interfaces.service;

import com.kynsof.identity.domain.dto.BusinessDto;
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
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}