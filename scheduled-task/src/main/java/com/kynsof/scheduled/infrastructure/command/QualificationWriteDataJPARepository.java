package com.kynsof.scheduled.infrastructure.command;

import com.kynsof.scheduled.infrastructure.entity.Qualification;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationWriteDataJPARepository extends JpaRepository<Qualification, UUID> {
}
