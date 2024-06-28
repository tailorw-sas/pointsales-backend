package com.kynsof.calendar.infrastructure.repository.command;

import com.kynsof.calendar.infrastructure.entity.Turn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TurnWriteDataJPARepository extends JpaRepository<Turn, UUID> {
}
