package com.kynsof.identity.infrastructure.repository.query;

import com.kynsof.identity.infrastructure.identity.RoleSystem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface RolReadDataJPARepository extends JpaRepository<RoleSystem, UUID>,
        JpaSpecificationExecutor<RoleSystem> {
    Page<RoleSystem> findAll(Specification specification, Pageable pageable);
}
