package com.kynsof.identity.infrastructure.repository.command;

import com.kynsof.identity.infrastructure.identity.PaymentDev;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentDevWriteDataJPARepository extends JpaRepository<PaymentDev, UUID> {
}
