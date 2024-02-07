package com.kynsof.scheduled.infrastructure.command;

import com.kynsof.scheduled.infrastructure.entity.Receipt;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptWriteDataJPARepository extends JpaRepository<Receipt, UUID> {
}
