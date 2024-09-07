package com.kynsof.calendar.infrastructure.repository.command;

import com.kynsof.calendar.infrastructure.entity.FirebaseToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FirebaseTokenWriteDataJPARepository extends JpaRepository<FirebaseToken, UUID> {
}
