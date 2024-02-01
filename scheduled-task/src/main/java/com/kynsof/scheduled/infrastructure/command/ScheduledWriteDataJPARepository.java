package com.kynsof.scheduled.infrastructure.command;

import java.util.UUID;

import com.kynsof.scheduled.infrastructure.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledWriteDataJPARepository extends JpaRepository<Schedule, UUID> {
}
