package com.kynsof.calendar.infrastructure.repository.query;

import com.kynsof.calendar.infrastructure.entity.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TurnReadDataJPARepository extends JpaRepository<Turn, UUID>, JpaSpecificationExecutor<Turn> {
    @Query("SELECT t FROM Turn t WHERE t.services.id =:serviceId and  t.business.id =:businessId")
    List<Turn> findByServiceId(UUID serviceId, UUID businessId);

////    @Query("SELECT maxelement(t.position) FROM Turn t WHERE t.services.id =:serviceId and  t.business.id =:businessId")
//    int findPositionByServiceId(UUID serviceId, UUID businessDtoId);
}
