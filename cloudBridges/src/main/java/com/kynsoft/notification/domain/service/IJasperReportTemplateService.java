package com.kynsoft.notification.domain.service;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsoft.notification.domain.dto.JasperReportTemplateDto;
import java.util.List;

import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IJasperReportTemplateService {
    void create(JasperReportTemplateDto object);
    void update(JasperReportTemplateDto object);
    void delete(UUID id);
    JasperReportTemplateDto findById(UUID id);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
