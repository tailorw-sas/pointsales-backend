package com.kynsoft.notification.infrastructure.repository.command;

import com.kynsoft.notification.infrastructure.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CampaignWriteRepository extends JpaRepository<Campaign, UUID> {

}
