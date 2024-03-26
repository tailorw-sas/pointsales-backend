package com.kynsof.identity.infrastructure.repository.query;

import com.kynsof.identity.infrastructure.identity.Permission;
import com.kynsof.identity.infrastructure.identity.RolePermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface RolPermissionReadDataJPARepository extends JpaRepository<RolePermission, UUID>,
        JpaSpecificationExecutor<RolePermission> {
    Page<RolePermission> findAll(Specification specification, Pageable pageable);
    Long countByPermissionAndDeletedFalse(Permission permission);
}
