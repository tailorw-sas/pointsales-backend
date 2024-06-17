package com.kynsoft.rrhh.infrastructure.repository.command;

import com.kynsoft.rrhh.infrastructure.identity.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserSystemsWriteDataJPARepository extends JpaRepository<UserSystem, UUID> {

}
