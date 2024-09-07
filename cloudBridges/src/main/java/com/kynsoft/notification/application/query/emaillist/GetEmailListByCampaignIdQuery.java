package com.kynsoft.notification.application.query.emaillist;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

@Getter
public class GetEmailListByCampaignIdQuery implements IQuery {

    private final String campaignId;

    public GetEmailListByCampaignIdQuery(String campaignId) {
        this.campaignId = campaignId;
    }
}
