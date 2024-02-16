package com.kynsof.calendar.infrastructure.repository.command;

import com.kynsof.calendar.infrastructure.entity.Qualification;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationWriteDataJPARepository extends JpaRepository<Qualification, UUID> {
}
