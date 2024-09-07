package com.kynsoft.notification.domain.service;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsoft.notification.domain.dto.CampaignDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface CampaignService {

    UUID createCampaign(CampaignDto campaignToCreate);

    void updateCampaign(CampaignDto campaignToUpdate);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filter);

    void sendEmailCampaign(String campaignId);

    CampaignDto getCampaignById(String campaignId);

    PaginatedResponse getAllCampaign(Pageable pageable);

    CampaignDto findById(String campaignId);
}
