package com.kynsof.treatments.infrastructure.repositories.command;

import com.kynsof.treatments.infrastructure.entity.OptometryExam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OptometryExamenWriteDataJPARepository extends JpaRepository<OptometryExam, UUID> {
}
