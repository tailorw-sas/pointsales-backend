package com.kynsof.shift.infrastructure.repository.query;

import com.kynsof.shift.infrastructure.entity.TurnerSpecialties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TurnerSpecialtiesReadDataJPARepository extends JpaRepository<TurnerSpecialties, UUID>, JpaSpecificationExecutor<TurnerSpecialties> {

    List<TurnerSpecialties> findByShiftDateTimeBefore(LocalDateTime shiftDateTime);
}

