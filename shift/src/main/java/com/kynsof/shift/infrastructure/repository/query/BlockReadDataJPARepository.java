package com.kynsof.shift.infrastructure.repository.query;

import com.kynsof.shift.infrastructure.entity.Block;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface BlockReadDataJPARepository extends JpaRepository<Block, UUID>, JpaSpecificationExecutor<Block> {
    Page<Block> findAll(Specification specification, Pageable pageable);

}
