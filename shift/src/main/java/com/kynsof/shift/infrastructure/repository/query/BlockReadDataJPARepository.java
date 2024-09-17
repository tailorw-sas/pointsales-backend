package com.kynsof.shift.infrastructure.repository.query;

import com.kynsof.shift.infrastructure.entity.Block;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BlockReadDataJPARepository extends JpaRepository<Block, UUID>, JpaSpecificationExecutor<Block> {
    Page<Block> findAll(Specification specification, Pageable pageable);

    @Query("SELECT COUNT(b) FROM Block b WHERE b.code = :code AND b.id <> :id")
    Long countByCodeAndNotId(@Param("code") String code, @Param("id") UUID id);

    @Query("SELECT COUNT(b) FROM Block b WHERE b.name = :name AND b.id <> :id")
    Long countByNameAndNotId(@Param("name") String name, @Param("id") UUID id);
}
