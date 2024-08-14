package com.kynsoft.notification.domain.dto;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TenantDto {

    private UUID id;
    private String tenantId;
    private OffsetDateTime createdAt;
}