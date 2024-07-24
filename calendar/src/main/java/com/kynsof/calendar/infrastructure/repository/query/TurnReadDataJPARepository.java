package com.kynsof.calendar.infrastructure.repository.query;

import com.kynsof.calendar.infrastructure.entity.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TurnReadDataJPARepository extends JpaRepository<Turn, UUID>, JpaSpecificationExecutor<Turn> {

    @Query("SELECT t FROM Turn t" +
            " WHERE t.services.id IN :serviceIds" +
            " AND t.business.id = :businessId" +
            " AND (t.status = 'PENDING' OR t.status = 'IN_PROGRESS')" +
            " ORDER BY t.createdAt ASC")
    List<Turn> findByServiceIds( List<UUID> serviceIds, UUID businessId);

    @Query("SELECT t FROM Turn t" +
            " WHERE t.isNeedPayment = true" +
            " AND t.business.id = :businessId" +
            " AND (t.status = 'PENDING' OR t.status = 'IN_PROGRESS')" +
            " ORDER BY t.createdAt ASC")
    List<Turn> findByServiceByFinanceId(UUID businessId);


    @Query("SELECT t FROM Turn t" +
            " WHERE t.local = :local" +
            " AND t.business.id = :businessId" +
            " AND (t.status = 'PENDING' OR t.status = 'IN_PROGRESS')" +
            " ORDER BY t.createdAt ASC")
    List<Turn> findByLocalId(String local, UUID businessId);
}

