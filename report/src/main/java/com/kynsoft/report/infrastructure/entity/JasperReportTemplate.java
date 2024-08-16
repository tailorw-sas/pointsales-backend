package com.kynsoft.report.infrastructure.entity;

import com.kynsof.share.core.domain.BaseEntity;
import com.kynsoft.report.domain.dto.DBConectionDto;
import com.kynsoft.report.domain.dto.JasperReportTemplateDto;
import com.kynsoft.report.domain.dto.JasperReportTemplateType;
import com.kynsoft.report.domain.dto.status.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jasper_report_template")
public class JasperReportTemplate extends BaseEntity {
    private String templateCode;
    private String templateName;
    private String templateDescription;
    private String templateContentUrl;
    @Enumerated(EnumType.STRING)
    private JasperReportTemplateType type;

    private String parameters;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "db_conection_id", nullable = true)
    private DBConection dbConection;

    private String query;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public JasperReportTemplate(JasperReportTemplateDto jasperReportTemplateDto) {
        this.id = jasperReportTemplateDto.getId();
        this.templateCode = jasperReportTemplateDto.getTemplateCode();
        this.templateName = jasperReportTemplateDto.getTemplateName();
        this.templateDescription = jasperReportTemplateDto.getTemplateDescription();
        this.templateContentUrl = jasperReportTemplateDto.getTemplateContentUrl();
        this.type = jasperReportTemplateDto.getType();
        this.parameters = jasperReportTemplateDto.getParameters();
        this.dbConection = jasperReportTemplateDto.getDbConection() != null ? new DBConection(jasperReportTemplateDto.getDbConection()) : null;
        this.query = jasperReportTemplateDto.getQuery();
        this.status = jasperReportTemplateDto.getStatus();
    }

    public JasperReportTemplateDto toAggregate () {
        String templateContentUrlS = templateContentUrl != null ? templateContentUrl : null;
        DBConectionDto conectionDto = dbConection != null ? dbConection.toAggregate() : null;
        return new JasperReportTemplateDto(id, templateCode, templateName, templateDescription, templateContentUrlS, type, parameters, createdAt, conectionDto, query, status);
    }

}
