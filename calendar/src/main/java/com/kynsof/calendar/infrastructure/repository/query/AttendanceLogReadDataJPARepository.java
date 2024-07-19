package com.kynsof.calendar.infrastructure.repository.query;

import com.kynsof.calendar.infrastructure.entity.AttendanceLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AttendanceLogReadDataJPARepository extends JpaRepository<AttendanceLog, UUID>, JpaSpecificationExecutor<AttendanceLog> {
    Page<AttendanceLog> findAll(Specification specification, Pageable pageable);

    @Query("select a from AttendanceLog a where a.service.id = :serviceId and  a.business.id = :businessId and " +
            "a.status = 'AVAILABLE' order by a.createdAt asc ")
    List<AttendanceLog> getByServiceId(UUID serviceId, UUID businessId);
}
