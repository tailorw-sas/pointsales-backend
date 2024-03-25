package com.kynsof.identity.infrastructure.repository.query;

import com.kynsof.identity.infrastructure.identity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PermissionReadDataJPARepository extends JpaRepository<Permission, UUID>, JpaSpecificationExecutor<Permission> {
    Page<Permission> findAll(Specification specification, Pageable pageable);
    @Query("SELECT distinct p FROM Permission p JOIN p.rolePermissions rp WHERE rp.role.id = :roleId")
    List<Permission> findPermissionsByRoleId(UUID roleId);
}
