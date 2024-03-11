package com.kynsof.identity.infrastructure.repositories.command;

import com.kynsof.identity.infrastructure.identity.RolSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RolWriteDataJPARepository extends JpaRepository<RolSystem, UUID> {

}
