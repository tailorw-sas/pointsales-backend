package com.kynsoft.notification.infrastructure.repository.query;

import com.kynsoft.notification.infrastructure.entity.EmailList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface EmailListReadRepository extends JpaRepository<EmailList, UUID>, JpaSpecificationExecutor<EmailList> {

    Page<EmailList> findEmailListByCampaignId(UUID campaignId,Pageable pageable);
}
