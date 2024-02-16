package com.kynsof.calendar.infrastructure.repository.command;

import com.kynsof.calendar.infrastructure.entity.Business;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessWriteDataJPARepository extends JpaRepository<Business, UUID> {
}
