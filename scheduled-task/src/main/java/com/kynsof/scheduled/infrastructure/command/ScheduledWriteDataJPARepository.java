package com.kynsof.scheduled.infrastructure.command;

import com.kynsof.scheduled.infrastructure.dao.ScheduleDao;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledWriteDataJPARepository extends JpaRepository<ScheduleDao, UUID> {
}
