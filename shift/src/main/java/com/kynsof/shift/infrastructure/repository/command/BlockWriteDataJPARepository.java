package com.kynsof.shift.infrastructure.repository.command;

import com.kynsof.shift.infrastructure.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlockWriteDataJPARepository extends JpaRepository<Block, UUID> {
}
