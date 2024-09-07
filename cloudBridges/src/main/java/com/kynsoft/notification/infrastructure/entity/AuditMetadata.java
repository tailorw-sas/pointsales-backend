package com.kynsoft.notification.infrastructure.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

public class AuditMetadata {
    @CreatedDate
    private Instant createDate;
    @LastModifiedDate
    private Instant updateDate;
}
