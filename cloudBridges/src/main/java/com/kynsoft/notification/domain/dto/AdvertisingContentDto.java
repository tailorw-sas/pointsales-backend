package com.kynsoft.notification.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisingContentDto {
    private UUID id;
    private String title;
    private String description;
    private ContentType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String url;
    private String link;
    private TenantDto tenant;
}
