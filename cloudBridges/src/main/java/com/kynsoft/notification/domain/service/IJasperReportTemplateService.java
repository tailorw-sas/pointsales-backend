package com.kynsoft.notification.domain.service;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsoft.notification.domain.dto.JasperReportTemplateDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IJasperReportTemplateService {
    void create(JasperReportTemplateDto object);
    void update(JasperReportTemplateDto object);
    void delete(UUID id);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
    JasperReportTemplateDto findById(UUID id);
    JasperReportTemplateDto findByTemplateCode(String templateCode);
}
