package com.kynsof.identity.infrastructure.repository.command;

import com.kynsof.identity.infrastructure.identity.UserPermissionBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserPermissionBusinessWriteDataJPARepository extends JpaRepository<UserPermissionBusiness, UUID> {
    void deleteAllByUserIdAndBusinessId(UUID userId, UUID businessId);
}
