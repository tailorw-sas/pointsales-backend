package com.kynsof.calendar.infrastructure.repository.command;

import com.kynsof.calendar.infrastructure.entity.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttendanceLogWriteDataJPARepository extends JpaRepository<AttendanceLog, UUID> {
}
