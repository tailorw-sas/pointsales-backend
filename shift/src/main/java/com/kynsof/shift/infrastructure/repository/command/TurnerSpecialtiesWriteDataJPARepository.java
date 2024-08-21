package com.kynsof.shift.infrastructure.repository.command;

import com.kynsof.shift.infrastructure.entity.TurnerSpecialties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TurnerSpecialtiesWriteDataJPARepository extends JpaRepository<TurnerSpecialties, UUID> {
}
