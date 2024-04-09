package com.kynsof.rrhh.infrastructure.repository.query;

import com.kynsof.rrhh.infrastructure.identity.UserSystem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UserSystemReadDataJPARepository extends JpaRepository<UserSystem, UUID>,
        JpaSpecificationExecutor<UserSystem> {
    Page<UserSystem> findAll(Specification specification, Pageable pageable);

}
