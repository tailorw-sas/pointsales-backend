package com.kynsof.identity.infrastructure.repository.query;


import com.kynsof.identity.infrastructure.identity.Business;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BusinessReadDataJPARepository extends JpaRepository<Business, UUID>, JpaSpecificationExecutor<Business> {
    Page<Business> findAll(Specification specification, Pageable pageable);
    @Query("SELECT DISTINCT b FROM UserPermissionBusiness upb JOIN upb.business b WHERE upb.user.id = :userId")
    List<Business> findBusinessesByUserId(UUID userId);
}
