package com.kynsoft.notification.application.query.emaillist;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class GetEmailListByCampaignIdQuery implements IQuery {
    private final Pageable pageable;
    private final String campaignId;

    public GetEmailListByCampaignIdQuery(String campaignId, Pageable pageable) {
        this.campaignId = campaignId;
        this.pageable = pageable;
    }
}
