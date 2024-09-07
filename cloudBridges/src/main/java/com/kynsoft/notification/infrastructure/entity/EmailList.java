package com.kynsoft.notification.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.generator.EventType;

import java.util.UUID;

@Entity(name = "email_list")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailList {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(columnDefinition = "serial", name = "email_list_id")
    @Generated(event = EventType.INSERT)
    private Long emailListId;
    private String email;
    private String clientName;
    private String clientLastname;

    private AuditMetadata auditMetadata;
}
