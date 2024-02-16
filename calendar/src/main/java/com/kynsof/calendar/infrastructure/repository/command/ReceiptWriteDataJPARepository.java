package com.kynsof.calendar.infrastructure.repository.command;

import com.kynsof.calendar.infrastructure.entity.Receipt;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptWriteDataJPARepository extends JpaRepository<Receipt, UUID> {
}
