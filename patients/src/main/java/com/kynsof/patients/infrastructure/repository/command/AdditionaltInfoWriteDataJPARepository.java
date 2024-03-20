package com.kynsof.patients.infrastructure.repository.command;

import com.kynsof.patients.infrastructure.entity.AdditionalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdditionaltInfoWriteDataJPARepository extends JpaRepository<AdditionalInformation, UUID> {

}