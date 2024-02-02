package com.kynsof.scheduled.infrastructure.command;

import com.kynsof.scheduled.infrastructure.entity.Business;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessWriteDataJPARepository extends JpaRepository<Business, UUID> {
}
