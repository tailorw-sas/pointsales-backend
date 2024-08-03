package com.kynsof.calendar.infrastructure.repository.query;

import com.kynsof.calendar.infrastructure.entity.Turn;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TurnReadDataJPARepository extends JpaRepository<Turn, UUID>, JpaSpecificationExecutor<Turn> {

    @Query("SELECT t FROM Services s " +
            "JOIN s.turns t " +
            "WHERE s.id IN :serviceIds " +
            "AND t.business.id = :businessId " +
            "AND t.status = 'PENDING'" +
            "ORDER BY " +
            "CASE WHEN s.preferFlag = true THEN t.createdAt ELSE '2200-12-31' END ASC, " +
            "s.currentLoop ASC, " +
            "s.priorityCount DESC, " +
            "s.order ASC")
    List<Turn> findByServiceIds(@Param("serviceIds") List<UUID> serviceIds, @Param("businessId") UUID businessId);

    @Query("SELECT t FROM Turn t" +
            " WHERE t.business.id = :businessId" +
            " AND t.status = 'PENDING'" +
            " ORDER BY t.createdAt ASC")
    List<Turn> findByServiceByFinanceId(UUID businessId);


    @Query("SELECT t FROM Turn t" +
            " WHERE t.local = :local" +
            " AND t.business.id = :businessId" +
            " AND t.status = 'PENDING'" +
            " ORDER BY t.createdAt ASC")
    List<Turn> findByLocalId(String local, UUID businessId);


    @Query("SELECT MAX(t.orderNumber) FROM Turn t WHERE t.services.id = :serviceId and " +
            "t.business.id = :businessId")
    Integer findMaxOrderNumberByServiceId(@Param("serviceId") UUID serviceId, @Param("businessId") UUID businessId);
}

