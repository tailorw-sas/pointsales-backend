package com.kynsof.shift.infrastructure.repository.command;

import com.kynsof.shift.infrastructure.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceWriteDataJPARepository extends JpaRepository<Services, UUID> {
}
