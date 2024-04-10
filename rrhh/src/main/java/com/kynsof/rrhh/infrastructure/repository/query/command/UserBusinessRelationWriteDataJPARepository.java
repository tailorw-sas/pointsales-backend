package com.kynsof.rrhh.infrastructure.repository.query.command;

import com.kynsof.rrhh.infrastructure.identity.UserBusinessRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserBusinessRelationWriteDataJPARepository extends JpaRepository<UserBusinessRelation, UUID> {
}
