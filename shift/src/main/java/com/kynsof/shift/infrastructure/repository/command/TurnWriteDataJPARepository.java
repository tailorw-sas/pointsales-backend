package com.kynsof.shift.infrastructure.repository.command;

import com.kynsof.shift.infrastructure.entity.Turn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TurnWriteDataJPARepository extends JpaRepository<Turn, UUID> {
}
