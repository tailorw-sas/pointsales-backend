package com.kynsoft.rrhh.infrastructure.repository.command;

import com.kynsoft.rrhh.infrastructure.identity.UserBusinessRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserBusinessRelationWriteDataJPARepository extends JpaRepository<UserBusinessRelation, UUID> {
}
