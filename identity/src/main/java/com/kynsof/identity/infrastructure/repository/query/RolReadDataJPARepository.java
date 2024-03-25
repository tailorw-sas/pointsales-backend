package com.kynsof.identity.infrastructure.repository.query;

import com.kynsof.identity.infrastructure.identity.RoleSystem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RolReadDataJPARepository extends JpaRepository<RoleSystem, UUID>,
        JpaSpecificationExecutor<RoleSystem> {
    Page<RoleSystem> findAll(Specification specification, Pageable pageable);

    @Query("SELECT distinct r FROM RoleSystem r JOIN r.userRoleBusinesses urb WHERE urb.user.id = :userId AND urb.business.id = :businessId")
    List<RoleSystem> findRolesByUserIdAndBusinessId(UUID userId, UUID businessId);
}
