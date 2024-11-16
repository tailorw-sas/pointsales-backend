package com.kynsof.treatments.domain.service;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IConsultHistoryService {

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

}