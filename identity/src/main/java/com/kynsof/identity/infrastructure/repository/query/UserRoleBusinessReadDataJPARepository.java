package com.kynsof.identity.infrastructure.repository.query;

import com.kynsof.identity.infrastructure.identity.UserRoleBusiness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRoleBusinessReadDataJPARepository extends JpaRepository<UserRoleBusiness, UUID>, JpaSpecificationExecutor<UserRoleBusiness> {
    Page<UserRoleBusiness> findAll(Specification specification, Pageable pageable);

    @Query("SELECT COUNT(u) FROM UserRoleBusiness u WHERE u.user.id = :userId AND u.role.id = :roleId AND u.business.id = :businessId AND u.deleted = :deleted")
    Long countByUserIdAndRoleIdAndBusinessIdAndDeleted(@Param("userId") UUID userId, @Param("roleId") UUID roleId, @Param("businessId") UUID businessId, @Param("deleted") boolean deleted);

}
