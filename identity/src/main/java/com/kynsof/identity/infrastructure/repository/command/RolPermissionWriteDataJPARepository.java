package com.kynsof.identity.infrastructure.repository.command;

import com.kynsof.identity.infrastructure.identity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RolPermissionWriteDataJPARepository extends JpaRepository<RolePermission, UUID> {

}
