package com.kynsof.calendar.infrastructure.repository.command;

import com.kynsof.calendar.infrastructure.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceWriteDataJPARepository extends JpaRepository<Services, UUID> {
}
