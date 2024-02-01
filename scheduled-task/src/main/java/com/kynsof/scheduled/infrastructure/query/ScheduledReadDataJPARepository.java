package com.kynsof.scheduled.infrastructure.query;

import com.kynsof.scheduled.infrastructure.dao.ScheduleDao;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ScheduledReadDataJPARepository extends JpaRepository<ScheduleDao, UUID>, JpaSpecificationExecutor<ScheduleDao> {
    Page<ScheduleDao> findAll(Specification specification, Pageable pageable);
}
