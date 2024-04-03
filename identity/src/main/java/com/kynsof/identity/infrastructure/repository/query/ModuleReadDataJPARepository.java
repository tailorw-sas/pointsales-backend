package com.kynsof.identity.infrastructure.repository.query;

import com.kynsof.identity.infrastructure.identity.ModuleSystem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ModuleReadDataJPARepository extends JpaRepository<ModuleSystem, UUID>, JpaSpecificationExecutor<ModuleSystem> {
    Page<ModuleSystem> findAll(Specification specification, Pageable pageable);

    @Query("SELECT DISTINCT m FROM ModuleSystem m " +
            "JOIN m.permissions p " +
            "JOIN p.rolePermissions rp " +
            "WHERE rp.role.id = :roleId AND rp.deleted = false ")
    List<ModuleSystem> findModulesByRoleId(UUID roleId);
}
