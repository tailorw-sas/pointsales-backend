package com.kynsoft.notification.domain.service;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsoft.notification.domain.dto.TenantDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ITenantService {
    void create(TenantDto object);
    void update(TenantDto object);
    void delete(UUID id);
    TenantDto findById(UUID id);
    TenantDto findByTenantId(String tenantId);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
