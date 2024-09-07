package com.kynsoft.notification.application.query.campaign.getAllCampaign;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class GetAllCampaignQuery implements IQuery {
    private final Pageable pageable;

    public GetAllCampaignQuery(Pageable pageable) {
        this.pageable = pageable;
    }
}
