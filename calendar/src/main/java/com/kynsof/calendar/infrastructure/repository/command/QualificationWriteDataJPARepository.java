package com.kynsof.calendar.infrastructure.repository.command;

import com.kynsof.calendar.infrastructure.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QualificationWriteDataJPARepository extends JpaRepository<Qualification, UUID> {
}
