package com.kynsoft.notification.application.query.campaign.getById;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsoft.notification.domain.service.CampaignService;
import org.springframework.stereotype.Component;

@Component
public class GetCampaignByIdQueryHandler implements IQueryHandler<GetCampaignByIdQuery,GetCampaignByIdResponse> {

    private final CampaignService campaignService;

    public GetCampaignByIdQueryHandler(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @Override
    public GetCampaignByIdResponse handle(GetCampaignByIdQuery query) {
        return new GetCampaignByIdResponse(campaignService.getCampaignById(query.getCampaignId()));
    }
}
