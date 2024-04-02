package com.kynsoft.notification.domain.service;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsoft.notification.domain.dto.AFileDto;
import java.util.List;

import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IAFileService {
    void create(AFileDto object);
    void update(AFileDto object);
    void delete(UUID id);
    AFileDto findById(UUID id);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
