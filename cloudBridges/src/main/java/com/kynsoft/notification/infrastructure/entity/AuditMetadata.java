package com.kynsoft.notification.infrastructure.entity;

import jakarta.persistence.EntityListeners;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@EntityListeners(AuditingEntityListener.class)
public class AuditMetadata {
    @CreatedDate
    private Instant createDate;
    @LastModifiedDate
    private Instant updateDate;
}
