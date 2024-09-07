package com.kynsoft.notification.infrastructure.repository.query;

import com.kynsoft.notification.infrastructure.entity.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CampaignReadRepository extends JpaRepository<Campaign, UUID>, JpaSpecificationExecutor<Campaign> {

    @Query(
            value = "select distinct campaign from campaign campaign left join fetch campaign.emailList",
            countQuery = "select count(distinct campaign ) from campaign campaign"
    )
    Page<Campaign> findAllWithEagerRelationships(Pageable pageable);


    @Query("select distinct campaign from campaign campaign left join fetch campaign.emailList where campaign.id =:id")
    Optional<Campaign> findOneWithEagerRelationships(@Param("id") UUID id);
}
