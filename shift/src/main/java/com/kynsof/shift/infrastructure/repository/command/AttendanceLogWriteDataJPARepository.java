package com.kynsof.shift.infrastructure.repository.command;

import com.kynsof.shift.infrastructure.entity.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttendanceLogWriteDataJPARepository extends JpaRepository<AttendanceLog, UUID> {
}
