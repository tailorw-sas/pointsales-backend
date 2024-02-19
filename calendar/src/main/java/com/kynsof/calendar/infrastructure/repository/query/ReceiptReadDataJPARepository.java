package com.kynsof.calendar.infrastructure.repository.query;

import com.kynsof.calendar.infrastructure.entity.Receipt;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReceiptReadDataJPARepository extends JpaRepository<Receipt, UUID>, JpaSpecificationExecutor<Receipt> {
    Page<Receipt> findAll(Specification specification, Pageable pageable);
}