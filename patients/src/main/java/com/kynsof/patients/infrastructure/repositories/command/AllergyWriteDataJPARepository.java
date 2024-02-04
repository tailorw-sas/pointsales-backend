package com.kynsof.patients.infrastructure.repositories.command;

import com.kynsof.patients.infrastructure.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AllergyWriteDataJPARepository extends JpaRepository<Allergy, UUID> {

}
