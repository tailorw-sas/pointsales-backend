package com.kynsof.identity.infrastructure.repository.query;

import com.kynsof.identity.infrastructure.identity.PaymentDev;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.UUID;

public interface PaymentDevReadDataJPARepository extends JpaRepository<PaymentDev, UUID>, JpaSpecificationExecutor<PaymentDev> {
    Page<PaymentDev> findAll(Specification specification, Pageable pageable);
}
