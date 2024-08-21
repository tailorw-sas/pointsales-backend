package com.kynsof.shift.infrastructure.repository.query;

import com.kynsof.shift.infrastructure.entity.TurnerSpecialties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.UUID;

public interface TurnerSpecialtiesReadDataJPARepository extends JpaRepository<TurnerSpecialties, UUID>, JpaSpecificationExecutor<TurnerSpecialties> {
}

