package com.kynsof.calendar.infrastructure.repository.command;

import com.kynsof.calendar.infrastructure.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduleWriteDataJPARepository extends JpaRepository<Schedule, UUID> {
}
