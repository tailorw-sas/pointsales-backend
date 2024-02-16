package com.kynsof.calendar.infrastructure.repository.command;

import java.util.UUID;

import com.kynsof.calendar.infrastructure.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleWriteDataJPARepository extends JpaRepository<Schedule, UUID> {
}
