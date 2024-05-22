package com.kynsof.calendar.infrastructure.repository.query;

import com.kynsof.calendar.infrastructure.entity.Receipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReceiptReadDataJPARepository extends JpaRepository<Receipt, UUID>, JpaSpecificationExecutor<Receipt> {

    Page<Receipt> findAll(Specification specification, Pageable pageable);

    Optional<Receipt> findByRequestId(String requestId);

    @Query("SELECT b FROM Receipt b WHERE b.user.id = :user AND b.schedule.id <> :schedule")
    Optional<Receipt> findReceiptByUserIdAndScheduleId(@Param("user") UUID user, @Param("schedule") UUID schedule);
}
