package com.kynsof.scheduled.infrastructure.query;

import java.util.UUID;

import com.kynsof.scheduled.infrastructure.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ScheduleReadDataJPARepository extends JpaRepository<Schedule, UUID>, JpaSpecificationExecutor<Schedule> {
    Page<Schedule> findAll(Specification specification, Pageable pageable);
}
