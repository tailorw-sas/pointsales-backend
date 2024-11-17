package com.kynsof.treatments.infrastructure.repositories.command;

import com.kynsof.treatments.infrastructure.entity.BusinessProcedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessProcedureWriteDataJPARepository extends JpaRepository<BusinessProcedure, UUID> {
}
