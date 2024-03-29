package com.kynsof.identity.infrastructure.repository.command;

import com.kynsof.identity.infrastructure.identity.UserRoleBusiness;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRoleBusinessWriteDataJPARepository extends JpaRepository<UserRoleBusiness, UUID> {
}
