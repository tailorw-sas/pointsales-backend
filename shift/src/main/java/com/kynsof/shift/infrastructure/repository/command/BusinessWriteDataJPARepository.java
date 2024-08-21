package com.kynsof.shift.infrastructure.repository.command;

import com.kynsof.shift.infrastructure.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessWriteDataJPARepository extends JpaRepository<Business, UUID> {
}
