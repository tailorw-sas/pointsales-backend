package com.kynsof.patients.infrastructure.repository.command;

import com.kynsof.patients.infrastructure.entity.FirebaseToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FirebaseTokenWriteDataJPARepository extends JpaRepository<FirebaseToken, UUID> {
}
