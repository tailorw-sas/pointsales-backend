package com.kynsof.patients.infrastructure.repositories.command;

import com.kynsof.patients.infrastructure.entity.AdditionalInformation;
import com.kynsof.patients.infrastructure.entity.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdditionaltInfoWriteDataJPARepository extends JpaRepository<AdditionalInformation, UUID> {

}
