package com.kynsof.identity.domain.interfaces.service;

import com.kynsof.identity.domain.dto.BusinessDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IBusinessService {

    UUID create(BusinessDto object);

    void update(BusinessDto object);

    void delete(UUID id);

    BusinessDto findById(UUID id);

    BusinessDto getById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
