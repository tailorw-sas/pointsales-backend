package com.kynsoft.notification.application.query.campaign.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsoft.notification.domain.service.CampaignService;
import org.springframework.stereotype.Component;

@Component
public class SearchCampaignQueryHandler implements IQueryHandler<SearchCampaignQuery, PaginatedResponse> {

    private final CampaignService campaignService;

    public SearchCampaignQueryHandler(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @Override
    public PaginatedResponse handle(SearchCampaignQuery query) {
        return campaignService.search(query.getPageable(),query.getFilter());
    }
}
