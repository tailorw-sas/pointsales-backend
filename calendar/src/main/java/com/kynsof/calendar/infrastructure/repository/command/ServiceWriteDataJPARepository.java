package com.kynsof.calendar.infrastructure.repository.command;

import com.kynsof.calendar.infrastructure.entity.Services;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceWriteDataJPARepository extends JpaRepository<Services, UUID> {
}
