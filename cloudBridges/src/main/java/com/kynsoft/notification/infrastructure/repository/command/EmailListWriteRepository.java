package com.kynsoft.notification.infrastructure.repository.command;

import com.kynsoft.notification.infrastructure.entity.EmailList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailListWriteRepository extends JpaRepository<EmailList, UUID> {
}
