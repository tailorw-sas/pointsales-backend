package com.kynsof.identity.infrastructure.repositories.query;

import com.kynsof.identity.infrastructure.identity.RolSystem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface RolReadDataJPARepository extends JpaRepository<RolSystem, UUID>,
        JpaSpecificationExecutor<RolSystem> {
    Page<RolSystem> findAll(Specification specification, Pageable pageable);
}
