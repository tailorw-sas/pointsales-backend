package com.kynsof.identity.infrastructure.repositories.query;

import com.kynsof.identity.infrastructure.identity.Permission;
import com.kynsof.identity.infrastructure.identity.UserSystem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserSystemReadDataJPARepository extends JpaRepository<UserSystem, UUID>,
        JpaSpecificationExecutor<UserSystem> {
    Page<UserSystem> findAll(Specification specification, Pageable pageable);
    @Query("SELECT DISTINCT p FROM UserSystem u JOIN u.userRoles ur JOIN ur.rol r JOIN r.rolPermissions rp " +
            "JOIN rp.permission p WHERE u.id = :userId")
    List<Permission> findDistinctPermissionsByUserId(UUID userId);
}
