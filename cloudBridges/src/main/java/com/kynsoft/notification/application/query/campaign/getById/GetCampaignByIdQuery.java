package com.kynsoft.notification.application.query.campaign.getById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

@Getter
public class GetCampaignByIdQuery implements IQuery {
   private final String campaignId;

    public GetCampaignByIdQuery(String campaignId) {
        this.campaignId = campaignId;
    }
}
