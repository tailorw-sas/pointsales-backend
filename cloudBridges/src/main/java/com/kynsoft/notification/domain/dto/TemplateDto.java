package com.kynsoft.notification.domain.dto;

import com.kynsoft.notification.infrastructure.entity.TemplateEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateDto {
    private UUID id;
    private String templateCode;
    private String name;
    private String description;
    private MailjetConfigurationDto mailjetConfigurationDto;
    private TenantDto tenant;
    private LocalDateTime createdAt;

    public TemplateEntity toAggregate(){
        return new TemplateEntity(this);
    }
}