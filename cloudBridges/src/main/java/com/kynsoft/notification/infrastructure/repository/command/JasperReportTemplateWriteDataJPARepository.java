package com.kynsoft.notification.infrastructure.repository.command;

import com.kynsoft.notification.infrastructure.entity.JasperReportTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JasperReportTemplateWriteDataJPARepository extends JpaRepository<JasperReportTemplate, UUID> {
}
