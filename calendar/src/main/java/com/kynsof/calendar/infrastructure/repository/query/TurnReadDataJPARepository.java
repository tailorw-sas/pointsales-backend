package com.kynsof.calendar.infrastructure.repository.query;

import com.kynsof.calendar.infrastructure.entity.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TurnReadDataJPARepository extends JpaRepository<Turn, UUID>, JpaSpecificationExecutor<Turn> {

}
