package com.kynsof.identity.infrastructure.repository.query;

import com.kynsof.identity.infrastructure.identity.UserPermissionBusiness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;
import java.util.UUID;

public interface UserRoleBusinessReadDataJPARepository extends JpaRepository<UserPermissionBusiness, UUID>, JpaSpecificationExecutor<UserPermissionBusiness> {
    Page<UserPermissionBusiness> findAll(Specification specification, Pageable pageable);
    @Query("SELECT upb FROM UserPermissionBusiness upb WHERE upb.user.id = :userId AND upb.business.id = :businessId")
    Set<UserPermissionBusiness> findByUserAndBusiness(UUID userId, UUID businessId);

}
