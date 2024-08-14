package com.kynsof.calendar.infrastructure.repository.command;

import com.kynsof.calendar.infrastructure.entity.TurnerSpecialties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TurnerSpecialtiesWriteDataJPARepository extends JpaRepository<TurnerSpecialties, UUID> {
}
