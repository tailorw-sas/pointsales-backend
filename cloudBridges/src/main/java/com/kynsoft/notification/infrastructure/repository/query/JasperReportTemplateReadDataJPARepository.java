package com.kynsoft.notification.infrastructure.repository.query;

import com.kynsoft.notification.infrastructure.entity.JasperReportTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface JasperReportTemplateReadDataJPARepository extends JpaRepository<JasperReportTemplate, UUID>, JpaSpecificationExecutor<JasperReportTemplate> {
    Page<JasperReportTemplate> findAll(Specification specification, Pageable pageable);
}
